package banquemisr.presentation.screen.list_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import banquemisr.components.shared_components.AppAlertDialog
import banquemisr.components.shared_components.AppHorizontalDivider
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.CircularIndeterminateProgressBar
import banquemisr.components.shared_components.Gap
import banquemisr.components.shared_components.PullToRefreshBox
import banquemisr.presentation.R
import banquemisr.presentation.UiState
import banquemisr.presentation.screen.list_screen.components.MovieSection
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen ( viewModel: ListScreenViewModel)
{
    val state = viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    PullToRefreshBox(
        isRefreshing = state.value.isRefreshing,
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
                    text = "List Screen",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = SecondaryColor,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                Gap(height = 10)

                state.value.let { state ->

                    //upcomingMovies
                    when (state.upcomingMovies) {
                        is UiState.Loading -> {
                            CircularIndeterminateProgressBar()
                        }

                        is UiState.Success -> {
                            state.imageLoader?.let {
                                MovieSection(title = "Upcoming", isHorizontal = true, movies =
                                state.upcomingMovies.data, context = context, imageLoader = it,
                                    onClick = { movieId ->
                                        viewModel.onIntent(ListScreenIntent.MovieClicked(movieId = movieId))

                                    }
                                )
                            }
                        }

                        is UiState.Error -> {
                            AppAlertDialog(
                                showDialog = true,
                                title = stringResource(R.string.error),
                                description = state.upcomingMovies.message,
                                onRemoveHeadFromQueue = {
                                    //viewModel.onIntent(ListScreenIntent
                                    // .RemoveHeadMessageFromQueue)
                                },
                            )

                        }

                        is UiState.Idle -> {


                        }
                    }



                    Gap(height = 10)

                    AppHorizontalDivider()

                    Gap(height = 10)

                    //nowPlayingMovies
                    when (state.nowPlayingMovies) {
                        is UiState.Loading -> {
                            CircularIndeterminateProgressBar()
                        }

                        is UiState.Success -> {
                            state.imageLoader?.let {
                                MovieSection(title = "Now Playing",
                                    isHorizontal = false,
                                    movies = state
                                        .nowPlayingMovies.data,
                                    context = context,
                                    imageLoader = it,
                                    onClick = { movieId ->
                                        viewModel.onIntent(ListScreenIntent.MovieClicked(movieId = movieId))
                                    }
                                )
                            }
                        }

                        is UiState.Error -> {
                            AppAlertDialog(
                                showDialog = true,
                                title = stringResource(R.string.error),
                                description = state.nowPlayingMovies.message,
                                onRemoveHeadFromQueue = {
                                    //viewModel.onIntent(ListScreenIntent
                                    // .RemoveHeadMessageFromQueue)
                                },
                            )

                        }

                        is UiState.Idle -> {


                        }
                    }

                }



            }


        }

    )
}
