package banquemisr.presentation.screen.details_screen.ui


sealed class DetailsScreenIntent {
    data class LoadMovieDetails(val movieID:Int) : DetailsScreenIntent()
    object RefreshMovieDetails : DetailsScreenIntent()
    object DismissErrorDialog : DetailsScreenIntent()
    object ShownErrorDialog : DetailsScreenIntent()
    data class SaveMovieID(val movieID: Int) : DetailsScreenIntent()
}