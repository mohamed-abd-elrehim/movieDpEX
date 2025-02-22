package banquemisr.presentation.screen.details_screen.ui

import banquemisr.core.domain.ProgressBarState
import banquemisr.domain.model.Movie
import banquemisr.domain.model.MovieDetails
import coil.ImageLoader


data class DetailsScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movieDetails: MovieDetails? = null,
    val imageLoader: ImageLoader?,
    val isRefreshing: Boolean = false,

)
