package banquemisr.presentation.screen.details_screen.ui

import banquemisr.presentation.UiState
import coil.ImageLoader


data class DetailsScreenState(

    val movieDetails: UiState<MovieDetailsUiModel> = UiState.Idle,
    val imageLoader: ImageLoader?,
    val isRefreshing: Boolean = false,
    val alertDialogState: Boolean = false,

    )
