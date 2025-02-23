package banquemisr.components.shared_components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import banquemisr.components.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieRefreshIndicator(
    modifier: Modifier = Modifier,
    isRefreshing: Boolean,
    state: PullToRefreshState
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

    val shouldPlay = isRefreshing || state.distanceFraction > 0.1f
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = shouldPlay,
        clipSpec = LottieClipSpec.Progress(0f, state.distanceFraction.coerceIn(0f, 1f))
    )

    LaunchedEffect(shouldPlay) {
        if (!shouldPlay) {
            state.snapTo(0f) // Ensures animation resets when not active
        }
    }

    if (shouldPlay) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier =  modifier

        )
    }
}
