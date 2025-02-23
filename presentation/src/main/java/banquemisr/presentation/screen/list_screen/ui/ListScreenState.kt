package banquemisr.presentation.screen.list_screen.ui

import banquemisr.presentation.UiState
import coil.ImageLoader



data class ListScreenState(
    val nowPlayingMovies: UiState<List<MovieUiModel>> = UiState.Idle,
    val upcomingMovies: UiState<List<MovieUiModel>> = UiState.Idle,
    val imageLoader: ImageLoader?,
    val isRefreshing: Boolean = false,
    val alertDialogState: Boolean = false
)
