package banquemisr.presentation.screen.details_screen.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import banquemisr.domain.use_case.ResultState
import banquemisr.domain.use_case.interactors.FetchMovieDetails
import banquemisr.presentation.ProgressBarState
import banquemisr.presentation.UiState
import banquemisr.presentation.screen.details_screen.model.toUiModel
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val imageLoader: ImageLoader,
    private val fetchMovieDetails: FetchMovieDetails,

):ViewModel() {
    private val _state = MutableStateFlow(DetailsScreenState(imageLoader = imageLoader))
    val state = _state.asStateFlow()


    fun onIntent(intent: DetailsScreenIntent) {
        when (intent) {
            is DetailsScreenIntent.LoadMovieDetails -> fetchMovie(intent.isRefreshing)
            is DetailsScreenIntent.DismissErrorDialog -> _state.value = _state.value.copy(
                isError = false
            )

            is DetailsScreenIntent.ShownErrorDialog -> _state.value =
                _state.value.copy(isError = true)

            is DetailsScreenIntent.SaveMovieID -> {
                _state.value = _state.value.copy(movieID = intent.movieID)
                fetchMovie(false)
            }

        }
    }


    private fun fetchMovie(isRefreshing:Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieId = state.value.movieID ?: return@launch
            fetchMovieDetails(movieId).onStart {
                if (isRefreshing) { _state.update { it.copy(isRefreshing = true)}}
                _state.value = _state.value.copy(
                    movieDetails = UiState.Loading(
                        ProgressBarState.Loading
                    )
                )
            }.collect { dataState ->
                when (dataState) {
                    is ResultState.Success -> {
                        if (isRefreshing) { _state.update { it.copy(isRefreshing = false)}}
                        _state.value = _state.value.copy(
                            movieDetails = UiState.Success(
                                (dataState
                                    .data).toUiModel()
                            )
                        )

                    }

                    is ResultState.Error -> {
                        if (isRefreshing) { _state.update { it.copy(isRefreshing = false)}}

                        onIntent(DetailsScreenIntent.ShownErrorDialog)
                        _state.value =
                            _state.value.copy(movieDetails = UiState.Error(dataState.message))
                    }
                }

            }
        }

    }




}








