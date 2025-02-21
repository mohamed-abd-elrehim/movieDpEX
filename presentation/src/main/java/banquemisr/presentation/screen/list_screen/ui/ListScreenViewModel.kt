package banquemisr.presentation.screen.list_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.core.domain.DataState
import banquemisr.core.domain.UIComponent
import banquemisr.domain.use_case.interactors.FetchNowPlayingMovies
import banquemisr.domain.use_case.interactors.FetchUpcomingMovies
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val fetchNowPlayingMovies: FetchNowPlayingMovies,
    private val fetchUpcomingMovies: FetchUpcomingMovies,
    private val imageLoader: ImageLoader
) : ViewModel() {


    private val _state = MutableStateFlow(ListScreenState(imageLoader = imageLoader))
    val state = _state.asStateFlow()

    init {
        onIntent(ListScreenIntent.LoadMovies())
    }

    fun onIntent(intent: ListScreenIntent) {
        when (intent) {
            is ListScreenIntent.LoadMovies -> fetchMovies(
                intent.sortBy, intent.certificationCountry
            )

            is ListScreenIntent.RefreshMovies -> onPageRefresh()
            is ListScreenIntent.MovieClicked -> onMovieClicked(intent.movieId)
        }

    }

    fun onEvent(event: ListScreenEvent) {
        when (event) {
            is ListScreenEvent.NavigateToMovieDetails -> {
                // logger.log("Navigating to movie details: ${event.movieId}")
            }
        }
    }


    private fun fetchMovies(sortBy: String, certificationCountry: String) {
        fetchNowPlaying(sortBy, certificationCountry)
        fetchUpcoming(sortBy, certificationCountry)


    }


    private fun fetchNowPlaying(sortBy: String, certificationCountry: String) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchNowPlayingMovies(sortBy, certificationCountry).collect { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        handelResponse(dataState.uiComponent)
                    }

                    is DataState.Data -> {
                        _state.value =
                            _state.value.copy(nowPlayingMovies = dataState.data ?: emptyList())
                    }

                    is DataState.Loading -> {
                        _state.value =
                            _state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }

            }


        }
    }


    private fun fetchUpcoming(sortBy: String, certificationCountry: String) {
        viewModelScope.launch(Dispatchers.IO) {

            fetchUpcomingMovies(sortBy, certificationCountry).collect { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        handelResponse(dataState.uiComponent)
                    }

                    is DataState.Data -> {
                        _state.value =
                            _state.value.copy(upcomingMovies = dataState.data ?: emptyList())
                    }

                    is DataState.Loading -> {
                        _state.value =
                            _state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }

            }
        }
    }


    private fun onPageRefresh() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.value = _state.value.copy(isRefreshing = true)

            onIntent(ListScreenIntent.LoadMovies())
            delay(1000)
            _state.value = _state.value.copy(isRefreshing = false)

        }
    }


    private fun onMovieClicked(movieId: Int) {
        //logger.log("onMovieClicked: $movieId")
    }


    private fun handelResponse(uiComponent: UIComponent) {
        when (uiComponent) {
            is UIComponent.Dialog -> {
                uiComponent.description?.let {
                    //logger.log(it)

                }

            }

            is UIComponent.None -> {
                uiComponent.message?.let { //logger.log(it)
                }

            }

        }
    }


}