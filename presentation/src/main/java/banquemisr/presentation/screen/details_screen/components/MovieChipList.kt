package banquemisr.presentation.screen.details_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import banquemisr.presentation.ui.theme.SecondaryColor
import banquemisr.presentation.ui.theme.ThirdColor


@Composable
fun MovieChipList(
    genres: List<String>
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(genres.size) { index ->
            MovieChip(genre = genres[index], chipColor = ThirdColor, chipTextColor = SecondaryColor)
        }
    }
}
