package banquemisr.presentation.screen.details_screen.ui

import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.Queue
import banquemisr.core.domain.UIComponent
import banquemisr.domain.model.Movie
import banquemisr.domain.model.MovieDetails
import coil.ImageLoader


data class DetailsScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val movieDetails: MovieDetails? = null,
    val imageLoader: ImageLoader?,
    val isRefreshing: Boolean = false,
    val alertDialogState: Boolean = false,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf())

)
