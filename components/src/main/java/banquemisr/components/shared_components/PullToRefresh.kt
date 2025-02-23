package banquemisr.components.shared_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


/*
Why Use rememberPullToRefreshState()?
State Management: It keeps track of the pull-to-refresh state, ensuring UI updates correctly when a refresh is triggered.
Recomposition Optimization: Since it is remembered using remember, it persists across recompositions, avoiding unnecessary re-creation.
Integration with UI: It's commonly used with PullToRefreshIndicator or similar components to show a loading spinner when refreshing.
 */
@Composable
@ExperimentalMaterial3Api
fun PullToRefreshBox(
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    state: PullToRefreshState = rememberPullToRefreshState(),
    contentAlignment: Alignment = Alignment.TopStart,
    indicator: @Composable BoxScope.() -> Unit = {
        MovieRefreshIndicator(
            modifier = Modifier.background(shape = RoundedCornerShape(50.dp), color =  Color.White
                .copy
                    (alpha = 0.5f)).size(80.dp)
                .align
                (Alignment.TopCenter),
            isRefreshing = isRefreshing,
            state = state
        )
    },
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier.pullToRefresh(state = state, isRefreshing = isRefreshing, onRefresh = onRefresh),
        contentAlignment = contentAlignment
    ) {

        content()
        indicator()
    }
}