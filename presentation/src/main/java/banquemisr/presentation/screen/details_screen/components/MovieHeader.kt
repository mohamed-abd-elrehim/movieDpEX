package banquemisr.presentation.screen.details_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import banquemisr.components.shared_components.AppText
import banquemisr.domain.model.MovieDetails

@Composable
fun MovieHeader(
    movieDetails: MovieDetails,
) {


    AppText(
        text = movieDetails.title,
        color = Color.White,
        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
    )

    AppText(
        text = (movieDetails.tagline ?: "Movie Tagline"),
        color = Color.White.copy(alpha = 0.8f),
        style = MaterialTheme.typography.bodyLarge,
    )


}