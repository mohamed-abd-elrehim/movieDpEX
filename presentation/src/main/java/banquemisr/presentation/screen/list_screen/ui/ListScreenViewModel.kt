package banquemisr.presentation.screen.list_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.ResultState
import banquemisr.domain.use_case.interactors.FetchNowPlayingMovies
import banquemisr.domain.use_case.interactors.FetchUpcomingMovies
import banquemisr.presentation.ProgressBarState
import banquemisr.presentation.UiState
import banquemisr.presentation.screen.list_screen.model.MovieUiModel
import banquemisr.presentation.screen.list_screen.model.toUiModel
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val fetchNowPlayingMovies: FetchNowPlayingMovies,
    private val fetchUpcomingMovies: FetchUpcomingMovies,
    private val imageLoader: ImageLoader,

) : ViewModel() {

    private val TAG = "ListScreenViewModel"
    private val _state = MutableStateFlow(ListScreenState(imageLoader = imageLoader))
    val state = _state.asStateFlow()



    fun onIntent(intent: ListScreenIntent) {
        when (intent) {
            is ListScreenIntent.LoadMovies -> fetchAllMovies()
            is ListScreenIntent.RefreshMovies -> onPageRefresh()


            is ListScreenIntent.DismissErrorDialog -> _state.update {
                it.copy(isError = true)
            }

            is ListScreenIntent.ShowErrorDialog -> _state.update {
                it.copy(isError = true)
            }

        }

    }


    private fun fetchAllMovies() {
        viewModelScope.launch {
            launch {
                fetchMovies(
                    fetchFunction = { fetchUpcomingMovies() },
                    updateState = { newState -> _state.update { it.copy(upcomingMovies = newState) } },
                    onError = { onIntent(ListScreenIntent.ShowErrorDialog) }
                )
            }

            launch {
                fetchMovies(
                    fetchFunction = { fetchNowPlayingMovies() },
                    updateState = { newState -> _state.update { it.copy(nowPlayingMovies = newState) } },
                    onError = { onIntent(ListScreenIntent.ShowErrorDialog) }
                )
            }
        }
    }
    private fun fetchMovies(
        fetchFunction: suspend () -> Flow<ResultState<List<MovieDomainModel>>>,
        updateState: (UiState<List<MovieUiModel>>) -> Unit,
        onError: () -> Unit

    ) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchFunction().onStart {
                updateState(UiState.Loading(ProgressBarState.Loading))
            }.collect { dataState ->
                val newState = when (dataState) {
                    is ResultState.Success -> UiState.Success(dataState.data.toUiModel())
                    is ResultState.Error -> {
                        onError()
                        UiState.Error(dataState.message)
                    }
                }
                updateState(newState)
            }
        }
    }



    private fun onPageRefresh() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.value = _state.value.copy(isRefreshing = ProgressBarState.Loading)

            onIntent(ListScreenIntent.LoadMovies)
            delay(1000)
            _state.value = _state.value.copy(isRefreshing = ProgressBarState.Idle)

        }
    }



}
