package banquemisr.presentation.screen.list_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.AppAlertDialog
import banquemisr.components.shared_components.AppHorizontalDivider
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.CircularIndeterminateProgressBar
import banquemisr.components.shared_components.Gap
import banquemisr.components.shared_components.PullToRefreshBox
import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.UIComponent
import banquemisr.presentation.screen.list_screen.components.MovieSection
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen ( viewModel: ListScreenViewModel)
{
    val state = viewModel.state.collectAsState()
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


                state.value.imageLoader?.let {
                    MovieSection(title = "Upcoming", isHorizontal = true ,movies = state.value
                        .upcomingMovies,context = context,imageLoader = it,
                        onClick = {movieId->
                            viewModel.onIntent(ListScreenIntent.MovieClicked(movieId = movieId))

                        }
                    )
                }
                Gap(height = 10)

                AppHorizontalDivider()

                Gap(height = 10)

                state.value.imageLoader?.let {
                    MovieSection(title = "Now Playing", isHorizontal = false, movies = state.value
                        .nowPlayingMovies,context = context,imageLoader = it,
                        onClick = {movieId->
                            viewModel.onIntent(ListScreenIntent.MovieClicked(movieId = movieId))
                        }
                    )
                }

            }
            if (state.value.errorQueue.isNotEmpty()) {

                state.value.errorQueue.peek()?.let { uiComponent ->
                    if (uiComponent is UIComponent.Dialog) {
                        AppAlertDialog (
                            showDialog = true,
                            title = uiComponent.title,
                            description = uiComponent.description,
                            onRemoveHeadFromQueue = {
                                viewModel.onIntent(ListScreenIntent.RemoveHeadMessageFromQueue)
                            },
                        )

                    }

                }


            }
            if (state.value.progressBarState is ProgressBarState.Loading) {
                CircularIndeterminateProgressBar()
            }
        }

    )
}
