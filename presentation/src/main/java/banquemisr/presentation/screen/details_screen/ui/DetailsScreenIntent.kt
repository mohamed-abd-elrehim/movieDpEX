package banquemisr.presentation.screen.details_screen.ui


sealed class DetailsScreenIntent {
    data class LoadMovieDetails(val movieID:Int) : DetailsScreenIntent()
    object RefreshMovieDetails : DetailsScreenIntent()
    object BackButtonClicked : DetailsScreenIntent()

    object DismissAlertDialog : DetailsScreenIntent()
    object ShownAlertDialog: DetailsScreenIntent()

    object IsMovieIDNull : DetailsScreenIntent()
    object DismissError : DetailsScreenIntent()



}