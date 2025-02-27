package banquemisr.presentation.screen.list_screen.ui

import banquemisr.presentation.ProgressBarState
import banquemisr.presentation.UiState
import banquemisr.presentation.screen.list_screen.model.MovieUiModel
import coil.ImageLoader


data class ListScreenState(
    val nowPlayingMovies: UiState<List<MovieUiModel>> = UiState.Loading(ProgressBarState.Idle),
    val upcomingMovies: UiState<List<MovieUiModel>> = UiState.Loading(ProgressBarState.Idle),
    val imageLoader: ImageLoader?,
    val isRefreshing:Boolean = false,
    val isError: Boolean = false,
    )
