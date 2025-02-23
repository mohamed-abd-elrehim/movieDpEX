package banquemisr.presentation.screen.list_screen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.core.domain.DataState
import banquemisr.core.domain.Queue
import banquemisr.core.domain.UIComponent
import banquemisr.core.util.Logger
import banquemisr.domain.use_case.interactors.FetchNowPlayingMovies
import banquemisr.domain.use_case.interactors.FetchUpcomingMovies
import banquemisr.presentation.navigation.NavToDetailsScreen
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val fetchNowPlayingMovies: FetchNowPlayingMovies,
    private val fetchUpcomingMovies: FetchUpcomingMovies,
    private val imageLoader: ImageLoader,
    @Named("ListScreenLogger") private val logger: Logger,
    private val navToDetailsScreen: NavToDetailsScreen

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
            is ListScreenIntent.MovieClicked -> onEvent(ListScreenEvent.NavigateToMovieDetails(intent.movieId))

            is ListScreenIntent.RemoveHeadMessageFromQueue -> {
                removeHeadMessage()
            }
        }

    }

    private fun onEvent(event: ListScreenEvent) {
        when (event) {
            is ListScreenEvent.NavigateToMovieDetails -> onMovieClicked(event.movieId)
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


    private fun onMovieClicked(movieId: Int){
        logger.log("onMovieClicked $movieId")
        navToDetailsScreen(movieId)
    }


    private fun handelResponse(uiComponent: UIComponent) {
        when (uiComponent) {
            is UIComponent.Dialog -> {
                uiComponent.description?.let {
                    uiComponent.description?.let { appendToMessageQueue (uiComponent) }

                }

            }

            is UIComponent.None -> {
                uiComponent.message?.let {
                    logger.log(it)
                }

            }

        }
    }

    private fun appendToMessageQueue(uiComponent: UIComponent){
        val queue = _state.value.errorQueue
        queue.add(uiComponent)
        _state.value = _state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = _state.value.errorQueue
            queue.poll() // remove first item

            // Create a new Queue instance to trigger recomposition
            val newQueue = Queue(queue.items.toMutableList())

            _state.value = _state.value.copy(
                errorQueue = newQueue,  // Assign a new reference
                alertDialogState =  _state.value.errorQueue.isNotEmpty())

            logger.log("Head message removed ${_state.value.errorQueue} ${_state.value.alertDialogState}")
        } catch (e: Exception) {
            logger.log("Nothing to remove from DialogQueue")
        }
    }

}
