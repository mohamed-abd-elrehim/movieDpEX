package banquemisr.components.shared_components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import banquemisr.components.ui.theme.PrimaryColor
import banquemisr.components.ui.theme.SecondaryColor


@Composable
fun AppAlertDialog(
    modifier: Modifier = Modifier,
    title: String,
    showDialog: Boolean,
    description: String? = null,
    onRemoveHeadFromQueue: () -> Unit,
) {
    if (!showDialog) return

    AppDialog(
        showDialog = showDialog,
        modifier = modifier.padding(0.dp),
        onDismiss = onRemoveHeadFromQueue,
        content = {
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
                    AppText(
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
                        onClick =  onRemoveHeadFromQueue,
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

    )

}

