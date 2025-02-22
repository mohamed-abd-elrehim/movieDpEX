package banquemisr.presentation.screen.details_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.AppText




@Composable
fun MovieDetailItem(
    image: Painter? = null,
    icon: ImageVector? = null,
    contentDescription: String,
    text: String
) {
    Row(
        modifier = Modifier.padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        image?.let {
            Image(
                painter = it,
                contentDescription = contentDescription,
                modifier = Modifier.size(25.dp),
                colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.8f))
            )
        }
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = contentDescription,
                tint = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.size(25.dp)
            )
        }
        AppText(
            text = text,
            color = Color.White.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}