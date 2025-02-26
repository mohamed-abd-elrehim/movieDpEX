package banquemisr.components.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import banquemisr.components.ui.theme.PrimaryColor
import banquemisr.components.ui.theme.SecondaryColor

@Composable
fun AppAlertDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    isShowDialog: Boolean = false,
) {
    if (!isShowDialog) return
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Surface(
            modifier = modifier.clip(RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    AppIconButton(
                        modifier = Modifier
                            .padding(5.dp),
                        iconTint = PrimaryColor,
                        iconModifier = Modifier
                            .background(
                                SecondaryColor.copy(alpha = 0.7f),
                                RoundedCornerShape(15.dp)
                            )
                            .size(50.dp)
                            .padding(5.dp),
                        onClick = { onDismissRequest() },
                    )
                }

                description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

