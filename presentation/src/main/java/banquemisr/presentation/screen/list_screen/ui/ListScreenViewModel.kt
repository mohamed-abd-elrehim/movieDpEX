package banquemisr.presentation.screen.list_screen.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.interactors.FetchNowPlayingMovies
import banquemisr.domain.use_case.interactors.FetchUpcomingMovies
import banquemisr.presentation.UiState
import banquemisr.presentation.navigation.NavToDetailsScreen
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
    private val navToDetailsScreen: NavToDetailsScreen

) : ViewModel() {

    val TAG = "ListScreenViewModel"
    private val _state = MutableStateFlow(ListScreenState(imageLoader = imageLoader))
    val state = _state.asStateFlow()

    init {
        onIntent(ListScreenIntent.LoadMovies)
    }

    fun onIntent(intent: ListScreenIntent) {
        when (intent) {
            is ListScreenIntent.LoadMovies -> fetchAllMovies()
            is ListScreenIntent.RefreshMovies -> onPageRefresh()
            is ListScreenIntent.MovieClicked -> onEvent(ListScreenEvent.NavigateToMovieDetails(intent.movieId))


            is ListScreenIntent.ShowUpcomingError -> _state.update {
                it.copy(isUpcomingError = true)
            }

            is ListScreenIntent.ShowNowPlayingError -> _state.update {
                it.copy(isNowPlayingError = true)
            }

            is ListScreenIntent.DismissUpcomingError -> _state.update {
                it.copy(isUpcomingError = false)
            }

            is ListScreenIntent.DismissNowPlayingError -> _state.update {
                it.copy(isNowPlayingError = false)
            }
        }

    }

    private fun onEvent(event: ListScreenEvent) {
        when (event) {
            is ListScreenEvent.NavigateToMovieDetails -> onMovieClicked(event.movieId)
        }
    }

    private fun fetchAllMovies() {
        viewModelScope.launch {
            launch {
                fetchMovies(
                    fetchFunction = { fetchUpcomingMovies() },
                    updateState = { newState -> _state.update { it.copy(upcomingMovies = newState) } },
                    onError = {onIntent(ListScreenIntent.ShowUpcomingError) }
                )
            }

            launch {
                fetchMovies(
                    fetchFunction = { fetchNowPlayingMovies() },
                    updateState = { newState -> _state.update { it.copy(nowPlayingMovies = newState) } },
                    onError = { onIntent(ListScreenIntent.ShowNowPlayingError) }
                )
            }
        }
    }
    private fun fetchMovies(
        fetchFunction: suspend () -> Flow<DomainState<List<MovieDomainModel>>>,
        updateState: (UiState<List<MovieUiModel>>) -> Unit,
        onError: () -> Unit

    ) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchFunction().onStart {
                updateState(UiState.Loading)
            }.collect { dataState ->
                val newState = when (dataState) {
                    is DomainState.Success -> UiState.Success(dataState.data.toUiModel())
                    is DomainState.Error ->{
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

            _state.value = _state.value.copy(isRefreshing = true)

            onIntent(ListScreenIntent.LoadMovies)
            delay(1000)
            _state.value = _state.value.copy(isRefreshing = false)

        }
    }


    private fun onMovieClicked(movieId: Int) {
        Log.d(TAG, "onMovieClicked $movieId")
        navToDetailsScreen(movieId)
    }

}
