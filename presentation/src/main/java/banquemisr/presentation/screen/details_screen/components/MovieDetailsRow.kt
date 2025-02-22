package banquemisr.presentation.screen.details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import banquemisr.domain.model.MovieDetails
import banquemisr.presentation.R

@Composable
fun MovieDetailsRow(movieDetails: MovieDetails?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        //  popularity
        MovieDetailItem(
            image = painterResource(id = R.drawable.popularity),
            contentDescription = stringResource(R.string.popularity),
            text = "%,.1f".format(movieDetails?.popularity ?: 0.0)

        )
// voteCount
        MovieDetailItem(
            icon = Icons.Default.FavoriteBorder,
            contentDescription = stringResource(R.string.vote_count),
            text = String.format("%d", movieDetails?.voteCount ?: 0)
        )
//  runtime
        MovieDetailItem(
            image = painterResource(id = R.drawable.runtime),
            contentDescription = stringResource(R.string.runtime),
            text = formatRuntime(movieDetails?.runtime ?: 0)
        )



    }
}

fun formatRuntime(runtimeMinutes: Int): String {
    val hours = runtimeMinutes / 60
    val minutes = runtimeMinutes % 60
    return String.format("%dh %02dm", hours, minutes)
}
