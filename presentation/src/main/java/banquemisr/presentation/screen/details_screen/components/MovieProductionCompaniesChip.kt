package banquemisr.presentation.screen.details_screen.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.AppText
import banquemisr.components.shared_components.LoadAsyncImage
import coil.ImageLoader
import com.google.android.material.chip.Chip

@Composable
fun MovieProductionCompaniesChip(productionCompanie: Pair<String,String>, chipColor:Color ,
      chipTextColor:Color,imageLoader: ImageLoader, context: Context
) {

    AssistChip(
        onClick = { /* Handle click */ },
        label = { AppText(text = productionCompanie.first, color = chipTextColor) },
        leadingIcon ={
            LoadAsyncImage(
                imageUrl = productionCompanie.second,
                imageTitle = productionCompanie.first,
                modifier = Modifier.size(20.dp),
                imageLoader = imageLoader,
                context = context,
                contentScale = ContentScale.Crop
            )
        },
        colors = AssistChipDefaults.assistChipColors(containerColor = chipColor)
    )

}