package banquemisr.presentation.screen.list_screen.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.Gap
import banquemisr.domain.model.Movie
import coil.ImageLoader


@Composable
fun MovieSection(title: String, isHorizontal: Boolean, movies: List<Movie>, imageLoader:
ImageLoader, context: Context, onClick: (Int) -> Unit = {}
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
