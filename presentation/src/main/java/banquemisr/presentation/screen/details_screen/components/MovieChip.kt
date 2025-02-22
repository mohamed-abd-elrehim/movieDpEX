package banquemisr.presentation.screen.details_screen.components

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import banquemisr.components.shared_components.AppText

@Composable
fun MovieChip(genre: String, chipColor:Color , chipTextColor:Color) {
    AssistChip(
        onClick = { },
        label = { AppText(text = genre, color = chipTextColor) },
        colors = AssistChipDefaults.assistChipColors
            (containerColor = chipColor)
    )
}