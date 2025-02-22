package banquemisr.presentation.screen.list_screen.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.Gap
import banquemisr.components.shared_components.PullToRefreshBox
import banquemisr.domain.model.Movie
import banquemisr.presentation.R
import banquemisr.presentation.screen.list_screen.components.MovieCard
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor
import coil.ImageLoader


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

                state.value.imageLoader?.let {
                    MovieSection(title = "Now Playing", isHorizontal = false, movies = state.value
                        .nowPlayingMovies,context = context,imageLoader = it,
                        onClick = {movieId->
                            viewModel.onIntent(ListScreenIntent.MovieClicked(movieId = movieId))
                        }
                    )
                }

            }


        }
    )
}

@Composable
fun MovieSectionTitle(title: String, isHorizontal: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Use fillMaxWidth instead of fillMaxSize
            .padding(horizontal = 10.dp, vertical = 5.dp), // Add some padding
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        AppText(
            text = title,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = Color.White
        )

        if (isHorizontal) {
            Icon(
                painter = painterResource(R.drawable.arrow_forward),
                contentDescription = stringResource(id = R.string.arrow_forward),
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }else{
            Icon(
                painter = painterResource(R.drawable.arrow_downward),
                contentDescription = stringResource(id = R.string.arrow_downward),
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}






@Composable
fun MovieSection(title: String, isHorizontal: Boolean,movies: List<Movie> , imageLoader:
ImageLoader , context: Context , onClick: (Int) -> Unit = {}
) {
    MovieSectionTitle (title = title, isHorizontal = isHorizontal)

    Gap(height = 5)

    if (isHorizontal) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(movies.size) { index ->
                MovieCard(movies[index],imageLoader,context = context,
                    onClick = { movieId->
                        onClick(movieId)


                    }
                )
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(movies.size) { index ->
                MovieCard(movies[index],imageLoader,context = context,
                    onClick = { movieId->
                        onClick(movieId)


                    }
                )
            }
        }
    }
}
