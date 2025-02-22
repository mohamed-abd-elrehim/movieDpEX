package banquemisr.presentation.screen.details_screen.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import banquemisr.components.shared_components.AppText

@Composable
fun SecondTitle (
    stringResource: Int
){
    AppText(
    text = stringResource(stringResource),
    color = Color.White.copy(alpha = 0.8f),
    style = MaterialTheme.typography.headlineMedium,
    textAlign = TextAlign.Start,

    )
}