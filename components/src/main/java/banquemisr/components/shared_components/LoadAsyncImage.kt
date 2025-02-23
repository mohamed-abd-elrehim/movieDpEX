package banquemisr.components.shared_components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
@Composable
fun LoadAsyncImage(
    context: Context,
    imageUrl: String,
    imageTitle: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop, // Default value for contentScale
    imageLoader: ImageLoader // Injected ImageLoader
) {
    ElevationCard(){
        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = imageTitle,
            imageLoader = imageLoader, // Use the injected ImageLoader
            modifier = modifier,
            contentScale = contentScale
        )
    }

}
