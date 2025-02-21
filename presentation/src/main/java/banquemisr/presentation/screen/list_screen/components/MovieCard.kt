package banquemisr.presentation.screen.list_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import banquemisr.components.shared_components.AppText
import banquemisr.presentation.R
import banquemisr.presentation.ui.theme.PrimaryColor
import banquemisr.presentation.ui.theme.SecondaryColor

@Composable
fun MovieCard() {
    ElevatedCard(
        onClick = { /* Do something */ },
        modifier = Modifier
            .size(150.dp, 200.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Movie Image
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )


            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, PrimaryColor, PrimaryColor),
                            startY = 0f,
                            endY = 2000f
                        )
                    )
                    .padding(3.dp)
            ){

                Row (
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .background(
                            SecondaryColor.copy(alpha = 0.7f),
                            RoundedCornerShape(15.dp)
                        )
                        .padding(10.dp),

                    ){
                    AppText(
                        text = "${String.format("%.2f", 4.5)}",
                        color = PrimaryColor,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = PrimaryColor,
                        modifier = Modifier.size(20.dp)
                    )

                }
                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                    .padding(start = 5.dp)
                ) {
                    AppText(
                        text = "Movie Title",
                        color = Color.White,
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    AppText(
                        text = ("Release Date"),
                        color = Color.White.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodyMedium
                    )

                }

            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun MovieCardPreview() {
    MovieCard()
}