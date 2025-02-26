package banquemisr.presentation.screen.list_screen.components

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import banquemisr.components.shared_components.AppAlertDialog
import banquemisr.components.shared_components.CircularIndeterminateProgressBar
import banquemisr.presentation.R
import banquemisr.presentation.UiState
import banquemisr.presentation.screen.list_screen.model.MovieUiModel
import coil.ImageLoader

@Composable
fun MovieUiStateHandler(
    title: String,
    uiState: UiState<List<MovieUiModel>>,
    context: Context,
    isAlertDialogState : Boolean,
    imageLoader: ImageLoader?,
    onMovieClick: (Int) -> Unit,
    onAlertDialogDismiss: () -> Unit,

) {
    when (uiState) {
        is UiState.Loading -> CircularIndeterminateProgressBar()
        is UiState.Success -> {
            imageLoader?.let {
                MovieSection(
                    title = title,
                    isHorizontal = title == stringResource(R.string.upcoming),
                    movies = uiState.data,
                    context = context,
                    imageLoader = it,
                    onClick = onMovieClick
                )
            }
        }

        is UiState.Error -> {
            AppAlertDialog(
                isShowDialog = isAlertDialogState,
                title = stringResource(R.string.error),
                description = uiState.message,
                onDismissRequest = onAlertDialogDismiss
            )
        }
    }
}