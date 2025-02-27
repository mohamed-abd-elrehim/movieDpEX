package banquemisr.presentation.screen.details_screen.ui

import banquemisr.presentation.ProgressBarState
import banquemisr.presentation.UiState
import banquemisr.presentation.screen.details_screen.model.MovieDetailsUiModel
import coil.ImageLoader


data class DetailsScreenState(
    val movieDetails: UiState<MovieDetailsUiModel> = UiState.Loading(ProgressBarState.Idle),
    val imageLoader: ImageLoader?,
    val isRefreshing: ProgressBarState = ProgressBarState.Idle,
    val isError: Boolean = false,
    val movieID: Int? = null

    )
