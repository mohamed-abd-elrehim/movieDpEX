package banquemisr.presentation.screen.list_screen.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.interactors.FetchNowPlayingMovies
import banquemisr.domain.use_case.interactors.FetchUpcomingMovies
import banquemisr.presentation.UiState
import banquemisr.presentation.navigation.NavToDetailsScreen
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
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
            is ListScreenIntent.LoadMovies -> fetchMovies()
            is ListScreenIntent.RefreshMovies -> onPageRefresh()
            is ListScreenIntent.MovieClicked -> onEvent(ListScreenEvent.NavigateToMovieDetails(intent.movieId))


        }

    }

    private fun onEvent(event: ListScreenEvent) {
        when (event) {
            is ListScreenEvent.NavigateToMovieDetails -> onMovieClicked(event.movieId)
        }
    }


    private fun fetchMovies() {
        fetchNowPlaying()
        fetchUpcoming()


    }



    private fun fetchUpcoming() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchUpcomingMovies().onStart {
                _state.value = _state.value.copy(upcomingMovies = UiState.Loading)

            }.onCompletion {
                _state.value = _state.value.copy(upcomingMovies = UiState.Idle)
            }.collect { dataState ->
                when (dataState) {
                    is DomainState.Success -> {
                        _state.value =
                            _state.value.copy(upcomingMovies = UiState.Success((dataState.data)
                                .toUiModel()))
                    }

                    is DomainState.Error -> {
                        _state.value =
                            _state.value.copy(upcomingMovies = UiState.Error(dataState.message))

                    }


                }
            }
        }
    }


    private fun fetchNowPlaying() {
        viewModelScope.launch(Dispatchers.IO) {

            fetchNowPlayingMovies().onStart {
                _state.value = _state.value.copy(nowPlayingMovies = UiState.Loading)

            }.onCompletion {
                _state.value = _state.value.copy(nowPlayingMovies = UiState.Idle)
            }.collect { dataState ->
                when (dataState) {
                    is DomainState.Success -> {
                        _state.value =
                            _state.value.copy(nowPlayingMovies = UiState.Success((dataState.data)
                                .toUiModel()))
                    }

                    is DomainState.Error -> {
                        _state.value =
                            _state.value.copy(nowPlayingMovies = UiState.Error(dataState.message))
                    }
                }

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
