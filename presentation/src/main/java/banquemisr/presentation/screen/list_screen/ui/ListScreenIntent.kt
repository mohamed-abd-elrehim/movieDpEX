package banquemisr.presentation.screen.list_screen.ui

sealed class ListScreenIntent {
    object  LoadMovies : ListScreenIntent()
    object RefreshMovies : ListScreenIntent()
    object DismissErrorDialog: ListScreenIntent()
    object ShowErrorDialog: ListScreenIntent()

}