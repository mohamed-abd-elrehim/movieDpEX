package banquemisr.presentation.screen.details_screen.ui


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.interactors.FetchMovieDetails
import banquemisr.presentation.UiState
import banquemisr.presentation.navigation.NavBack
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
class DetailsScreenViewModel @Inject constructor(
    private val imageLoader: ImageLoader,
    private val fetchMovieDetails: FetchMovieDetails,
    private val savedStateHandle: SavedStateHandle,
    private val navBack: NavBack


):ViewModel() {
    private val TAG = "DetailsScreenViewModel"

    private val _state = MutableStateFlow(DetailsScreenState(imageLoader = imageLoader))
    val state = _state.asStateFlow()

    init {
        savedStateHandle.get<Int>("movieId").let { movieId ->
            if (movieId != null)
                onIntent(DetailsScreenIntent.LoadMovieDetails(movieId))
            else {
                Log.d(TAG,"heroId is null")
            }

        }
    }

    fun onIntent(intent: DetailsScreenIntent) {
        when (intent) {
            is DetailsScreenIntent.LoadMovieDetails -> fetchMovie(intent.movieID)
            is DetailsScreenIntent.RefreshMovieDetails -> onPageRefresh()
            is DetailsScreenIntent.BackButtonClicked -> onEvent(DetailsScreenEvent.NavigateBackToListScreen)
        }
    }

    private fun onEvent(event: DetailsScreenEvent) {
        when (event) {
            is DetailsScreenEvent.NavigateBackToListScreen -> onButtonClicked()
        }
    }

    private fun fetchMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMovieDetails(movieId).onStart {
                _state.value = _state.value.copy(movieDetails = UiState.Loading)
            }.onCompletion {
                _state.value = _state.value.copy(movieDetails = UiState.Idle)
            }.collect { dataState ->
                when (dataState) {
                    is DomainState.Success-> {
                        _state.value = _state.value.copy(movieDetails = UiState.Success((dataState
                            .data).toUiModel()))
                    }
                    is DomainState.Error -> {
                        _state.value = _state.value.copy(movieDetails = UiState.Error(dataState.message))
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
                Log.d(TAG,"heroId is null")
            }

        }
    }


    private fun onButtonClicked() {
        navBack()

    }




}