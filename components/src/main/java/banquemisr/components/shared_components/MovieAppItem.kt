package banquemisr.components.shared_components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp




@Composable
fun MovieAppItem(
    rowModifier: Modifier = Modifier.padding(6.dp),
    image: Painter? = null,
    icon: ImageVector? = null,
    contentDescription: String,
    text: String,
    textColor: Color = Color.White.copy(alpha = 0.8f),
    textStyle:TextStyle= MaterialTheme.typography.bodyLarge,
    iconTint: Color = Color.White.copy(alpha = 0.8f),
    iconModifier: Modifier = Modifier.size(25.dp)


) {
    Row(
        modifier = Modifier
            .then(rowModifier),
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
                tint = iconTint,
                modifier = iconModifier
            )
        }
        AppText(
            text = text,
            color = textColor,
            style = textStyle
        )
    }
}