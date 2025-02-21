package banquemisr.presentation.screen.list_screen.ui

import banquemisr.core.domain.ProgressBarState
import banquemisr.domain.model.Movie
import coil.ImageLoader


data class ListScreenState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val nowPlayingMovies: List<Movie> = emptyList(),
    val upcomingMovies: List<Movie> = emptyList(),
    val imageLoader: ImageLoader?,
    val isRefreshing: Boolean = false,



)
