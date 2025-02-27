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
            is ListScreenIntent.LoadMovies -> fetchAllMovies(intent.isRefreshing)

            is ListScreenIntent.DismissErrorDialog -> _state.update {
                it.copy(isError = true)
            }

            is ListScreenIntent.ShowErrorDialog -> _state.update {
                it.copy(isError = true)
            }

        }

    }


    private fun fetchAllMovies( isRefreshing: Boolean = false) {
        viewModelScope.launch {
            launch {
                fetchMovies(
                    isRefreshing = isRefreshing,
                    fetchFunction = { fetchUpcomingMovies() },
                    updateState = { newState -> _state.update { it.copy(upcomingMovies = newState) } },
                    onError = { onIntent(ListScreenIntent.ShowErrorDialog) }
                )
            }

            launch {
                fetchMovies(
                    isRefreshing = isRefreshing,
                    fetchFunction = { fetchNowPlayingMovies() },
                    updateState = { newState -> _state.update { it.copy(nowPlayingMovies = newState) } },
                    onError = { onIntent(ListScreenIntent.ShowErrorDialog) }
                )
            }
        }
    }
    private fun fetchMovies(
        isRefreshing: Boolean ,
        fetchFunction: suspend () -> Flow<ResultState<List<MovieDomainModel>>>,
        updateState: (UiState<List<MovieUiModel>>) -> Unit,
        onError: () -> Unit

    ) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchFunction().onStart {
                updateState(UiState.Loading(ProgressBarState.Loading))
                if (isRefreshing) { _state.update { it.copy(isRefreshing = true) } }
            }.collect { dataState ->
                val newState = when (dataState) {
                    is ResultState.Success -> {
                        if (isRefreshing) { _state.update { it.copy(isRefreshing = false) } }
                        UiState.Success(dataState.data.toUiModel())
                    }
                    is ResultState.Error -> {
                        if (isRefreshing) { _state.update { it.copy(isRefreshing = false) } }
                        onError()
                        UiState.Error(dataState.message)
                    }
                }
                updateState(newState)
            }
        }
    }





}
