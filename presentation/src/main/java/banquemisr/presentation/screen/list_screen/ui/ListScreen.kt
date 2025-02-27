package banquemisr.presentation.screen.list_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import banquemisr.components.shared_components.AppHorizontalDivider
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.Gap
import banquemisr.components.shared_components.PullToRefreshBox
import banquemisr.presentation.ProgressBarState
import banquemisr.presentation.R
import banquemisr.presentation.screen.list_screen.components.MovieListContent
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    viewModel: ListScreenViewModel = hiltViewModel(),
    onMovieClick: (movieId: Int) -> Unit
)
{

    LaunchedEffect(Unit) {
        viewModel.onIntent(ListScreenIntent.LoadMovies)
    }

    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    PullToRefreshBox(
        isRefreshing = (state.value.isRefreshing == ProgressBarState.Loading),
        onRefresh = {

            viewModel.onIntent(ListScreenIntent.RefreshMovies)
        },
        content = {
            Column(
                modifier = Modifier
                    .background(PrimaryColor)
                    .fillMaxSize()

                    .padding(10.dp)
            ) {
                AppText(
                    text = stringResource(R.string.list_screen),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = SecondaryColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Gap(height = 10)

                // Upcoming Movies Section
                MovieListContent(
                    title = stringResource(R.string.upcoming),
                    uiState = state.value.upcomingMovies,
                    context = context,
                    imageLoader = state.value.imageLoader,
                    onMovieClick = onMovieClick,

                    isAlertDialogState = state.value.isError,
                    onAlertDialogDismiss = {
                        viewModel.onIntent(ListScreenIntent.DismissErrorDialog)
                    }
                )

                Gap(height = 10)
                AppHorizontalDivider()
                Gap(height = 10)

                // Now Playing Movies Section
                MovieListContent(
                    title = stringResource(R.string.now_playing),
                    uiState = state.value.nowPlayingMovies,
                    context = context,
                    imageLoader = state.value.imageLoader,
                    onMovieClick = onMovieClick,
                    isAlertDialogState = state.value.isError,
                    onAlertDialogDismiss = {
                        viewModel.onIntent(ListScreenIntent.DismissErrorDialog)
                    }
                )
            }


        }

    )
}
