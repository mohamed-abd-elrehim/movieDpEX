package banquemisr.presentation.screen.details_screen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.AppIconButton
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.Gap
import banquemisr.components.shared_components.LoadAsyncImage
import banquemisr.presentation.R
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor


@Composable
fun DetailsScreen(viewModel: DetailsScreenViewModel) {
    val state = viewModel.state.collectAsState()
    val context = LocalContext.current

    state.value.let { movie ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Movie Image


            movie.movieDetails.let { movieDetails ->

                movie.imageLoader?.let {
                    LoadAsyncImage(
                        imageUrl = movieDetails?.backdropPath ?: "",
                        imageTitle = movieDetails?.title ?: "",
                        modifier = Modifier.fillMaxSize(),
                        imageLoader = it,
                        context = context,
                        contentScale = ContentScale.FillBounds
                    )
                }
            }


            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, PrimaryColor, PrimaryColor),
                            startY = 0f,
                            endY = 3000f
                        )
                    )

            ) {
                AppIconButton(
                    icon = Icons.AutoMirrored.Filled.ArrowBack,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(5.dp),
                    iconTint = PrimaryColor,
                    iconModifier = Modifier
                        .background(SecondaryColor.copy(alpha = 0.7f), RoundedCornerShape(15.dp))
                        .size(50.dp)
                        .padding(5.dp),
                    onClick = { viewModel.onIntent(DetailsScreenIntent.BackButtonClicked) }
                )

                Column(
                    modifier = Modifier.align(Alignment.CenterStart),
                ) {

                    movie.movieDetails.let { movieDetails ->
                        AppText(
                            text = movieDetails?.title ?: "Movie Title",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Gap(height = 5)

                            AppText(
                                text = (movieDetails?.tagline?:""),
                                color = Color.White.copy(alpha = 0.8f),
                                style = MaterialTheme.typography.bodyLarge,)
                            Gap(height = 5)

                            AppText(
                                text = (stringResource(R.string.release_date, movieDetails?.releaseDate?:"") ),
                                color = Color.White.copy(alpha = 0.8f),
                                style = MaterialTheme.typography.bodyLarge,)




                    }
                }


                LazyColumn {

                }

            }

        }

    }

}
