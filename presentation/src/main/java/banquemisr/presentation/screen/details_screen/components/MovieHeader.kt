package banquemisr.presentation.screen.details_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import banquemisr.components.shared_components.AppText
import banquemisr.presentation.R
import banquemisr.presentation.screen.details_screen.ui.MovieDetailsUiModel

@Composable
fun MovieHeader(
    movieDetails: MovieDetailsUiModel,
) {


    AppText(
        text = movieDetails.title,
        color = Color.White,
        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
    )

    AppText(
        text = (movieDetails.tagline ?: stringResource(R.string.movie_tagline)),
        color = Color.White.copy(alpha = 0.8f),
        style = MaterialTheme.typography.bodyLarge,
    )


}