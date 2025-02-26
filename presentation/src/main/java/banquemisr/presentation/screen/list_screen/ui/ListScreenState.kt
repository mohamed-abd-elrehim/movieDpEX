package banquemisr.presentation.screen.list_screen.ui

import banquemisr.presentation.UiState
import banquemisr.presentation.screen.list_screen.model.MovieUiModel
import coil.ImageLoader



data class ListScreenState(
    val nowPlayingMovies: UiState<List<MovieUiModel>> = UiState.Loading,
    val upcomingMovies: UiState<List<MovieUiModel>> = UiState.Loading,
    val imageLoader: ImageLoader?,


    val isRefreshing: Boolean = false,
    val isUpcomingError: Boolean = false,
    val isNowPlayingError: Boolean = false,


    )
