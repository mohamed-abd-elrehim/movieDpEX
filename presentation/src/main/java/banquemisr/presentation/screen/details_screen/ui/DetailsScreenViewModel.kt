package banquemisr.presentation.screen.details_screen.ui


import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.core.domain.DataState
import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.Queue
import banquemisr.core.domain.UIComponent
import banquemisr.core.util.Logger
import banquemisr.domain.use_case.interactors.FetchMovieDetails
import banquemisr.presentation.navigation.NavBack
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
class DetailsScreenViewModel @Inject constructor(
    private val imageLoader: ImageLoader,
    @Named("DetailsScreenLogger") private val logger: Logger,
    private val fetchMovieDetails: FetchMovieDetails,
    private val savedStateHandle: SavedStateHandle,
    private val navBack: NavBack


):ViewModel() {
    private val _state = MutableStateFlow(DetailsScreenState(imageLoader = imageLoader))
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("movieId").let { movieId ->
            if (movieId != null)
                onIntent(DetailsScreenIntent.LoadMovieDetails(movieId))
            else {
                logger.log("heroId is null")
            }

        }
    }

    fun onIntent(intent: DetailsScreenIntent) {
        when (intent) {
            is DetailsScreenIntent.LoadMovieDetails -> fetchMovie(intent.movieID)
            is DetailsScreenIntent.RefreshMovieDetails -> onPageRefresh()
            is DetailsScreenIntent.BackButtonClicked -> onEvent(DetailsScreenEvent.NavigateBackToListScreen)
            is DetailsScreenIntent.RemoveHeadMessageFromQueue -> removeHeadMessage()
        }
    }

    private fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.NavigateBackToListScreen -> onButtonClicked()
        }
    }

    private fun fetchMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(ProgressBarState.Loading)
            fetchMovieDetails(movieId).collect { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        handelResponse(dataState.uiComponent)
                    }

                    is DataState.Data -> {
                        _state.value =
                            _state.value.copy(movieDetails = dataState.data)
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

        savedStateHandle.get<Int>("movieId").let { movieId ->
            if (movieId != null) {
                _state.value = _state.value.copy(isRefreshing = true)
                viewModelScope.launch(Dispatchers.IO) {
                    onIntent(DetailsScreenIntent.LoadMovieDetails(movieId))
                    delay(1000)
                    _state.value = _state.value.copy(isRefreshing = false)
                }
            } else {
                logger.log("heroId is null")
            }

        }
    }

    private fun onButtonClicked() {
        navBack()

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