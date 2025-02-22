package banquemisr.components.shared_components

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontWeight: FontWeight? = null,
    fontStyle: FontStyle? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textAlign: TextAlign = TextAlign.Start,
    style:TextStyle = TextStyle.Default,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,

    ) {

        Text(
            text = text,
            modifier = modifier,
            color = color,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontStyle = fontStyle,
            letterSpacing = letterSpacing,
            textAlign = textAlign,
            style = style,
            maxLines = maxLines,
            overflow = overflow
        )

}
