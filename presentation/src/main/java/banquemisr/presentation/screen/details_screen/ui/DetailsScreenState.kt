package banquemisr.presentation.screen.details_screen.ui

import banquemisr.presentation.UiState
import coil.ImageLoader


data class DetailsScreenState(

    val movieDetails: UiState<MovieDetailsUiModel> = UiState.Loading,
    val imageLoader: ImageLoader?,
    val isRefreshing: Boolean = false,
    val alertDialogState: Boolean = false,
    val movieId: String? = null,
    val errorMassge: String = "Movie ID is Not Found",
    val isError: Boolean = false,

    )
