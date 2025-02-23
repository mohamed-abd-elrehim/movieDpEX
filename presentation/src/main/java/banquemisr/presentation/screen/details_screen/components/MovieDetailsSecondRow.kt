package banquemisr.presentation.screen.details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.MovieAppItem
import banquemisr.domain.model.MovieDetails
import banquemisr.presentation.R

@Composable
fun MovieDetailsSecondRow(movieDetails: MovieDetails?) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        // voteAverage
        MovieAppItem(
            icon = Icons.Default.Star,
            contentDescription = stringResource(R.string.star),
            text = String.format("%.1f", movieDetails?.voteAverage ?: 0.0)
        )

        //releaseDate
        MovieAppItem(
            icon = Icons.Default.DateRange,
            contentDescription = stringResource(R.string.release_date),
            text = movieDetails?.releaseDate ?: ""
        )
        if (movieDetails?.budget != null && movieDetails?.budget != 0) {
            //  budget
            MovieAppItem(
                image = painterResource(id = R.drawable.budget),
                contentDescription = stringResource(R.string.budget),
                text = formatBudget(movieDetails?.budget ?: 0)
            )
        }


    }

}



fun formatBudget(budget: Int): String {
    return when {
        budget >= 1_000_000_000 -> String.format("$%.1fB", budget / 1_000_000_000.0)
        budget >= 1_000_000 -> String.format("$%.1fM", budget / 1_000_000.0)
        else -> "$$budget"
    }
}