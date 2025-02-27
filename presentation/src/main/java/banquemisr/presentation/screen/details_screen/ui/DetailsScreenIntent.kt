package banquemisr.presentation.screen.details_screen.ui


sealed class DetailsScreenIntent {
    data class LoadMovieDetails(val isRefreshing: Boolean = false) : DetailsScreenIntent()
    object DismissErrorDialog : DetailsScreenIntent()
    object ShownErrorDialog : DetailsScreenIntent()
    data class SaveMovieID(val movieID: Int) : DetailsScreenIntent()
}