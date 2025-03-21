package banquemisr.presentation.screen.details_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import banquemisr.components.shared_components.AppText
import banquemisr.presentation.screen.details_screen.model.MovieDetailsUiModel

@Composable
fun MovieHeader(
    movieDetails: MovieDetailsUiModel,
) {


    AppText(
        text = movieDetails.title,
        color = Color.White,
        style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
    )

    if (movieDetails.tagline != null) {
        AppText(
            text = (movieDetails.tagline),
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyLarge,
        )
    }


}