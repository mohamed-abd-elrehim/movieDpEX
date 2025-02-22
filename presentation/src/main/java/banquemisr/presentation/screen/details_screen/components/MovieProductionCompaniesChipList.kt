
package banquemisr.presentation.screen.details_screen.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import banquemisr.presentation.ui.theme.SecondaryColor
import banquemisr.presentation.ui.theme.ThirdColor
import coil.ImageLoader


@Composable
fun MovieProductionCompaniesChipList(
    productionCompanies: List<Pair<String,String>>,
    imageLoader: ImageLoader,
    context: Context
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(productionCompanies.size) { index ->
            MovieProductionCompaniesChip(productionCompanie = productionCompanies[index],
                chipColor = ThirdColor, chipTextColor = SecondaryColor,imageLoader = imageLoader, context = context)
        }
    }
}
