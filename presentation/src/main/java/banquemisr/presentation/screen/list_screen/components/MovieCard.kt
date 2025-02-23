package banquemisr.presentation.screen.list_screen.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.LoadAsyncImage
import banquemisr.components.shared_components.MovieAppItem
import banquemisr.domain.model.Movie
import banquemisr.presentation.R
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor
import coil.ImageLoader

@Composable
fun MovieCard(
    movie: Movie,
    imageLoader: ImageLoader,
    onClick: (Int) -> Unit = {},
    context: Context
) {
    ElevatedCard(
        onClick = { onClick(movie.id) },
        modifier = Modifier
            .size(150.dp, 200.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Movie Image
            movie.posterPath?.let {
                LoadAsyncImage(
                    imageUrl = it,
                    imageTitle = movie.title,
                    modifier = Modifier.fillMaxSize(),
                    imageLoader = imageLoader,
                    context = context,
                    contentScale = ContentScale.Crop
                )
            }



            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, PrimaryColor, PrimaryColor),
                            startY = 0f,
                            endY = 1000f
                        )
                    )
                    .padding(3.dp)
            ){


                MovieAppItem(
                    rowModifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            SecondaryColor.copy(alpha = 0.7f),
                            RoundedCornerShape(15.dp)
                        )
                        .padding(10.dp),
                    icon = Icons.Default.Star,
                    contentDescription = stringResource(R.string.star),
                        text = String.format("%.1f", movie.voteAverage),
                    iconModifier = Modifier.size(20.dp),
                    textColor = PrimaryColor,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    iconTint = PrimaryColor

                    )

                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    AppText(
                        text = movie.title,
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        fontSize = 20.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    MovieAppItem(
                        icon = Icons.Default.DateRange,
                        contentDescription = stringResource(R.string.release_date),
                        text = movie.releaseDate
                    )


                }

            }
        }
    }
}
@Composable
@Preview
fun MovieCardPreview() {
    MovieCard(
        movie = Movie(
            id = 1,
            title = "Movie Title",
            overview = "Movie overview",
            posterPath = "https://image.tmdb.org/t/p/w500/poster_path.jpg",
            backdropPath = "https://image.tmdb.org/t/p/w500/backdrop_path.jpg",
            releaseDate = "2023-07-01",
            voteAverage = 8.5,
            voteCount = 1000,
            genreIds = listOf(1, 2, 3),
            popularity = 7.8,
            adult = false,
            originalLanguage = "en",
            originalTitle = "Original Title",

            video = false
        ),
        imageLoader = ImageLoader.Builder(LocalContext.current).build(),
        context = LocalContext.current
    )
}

