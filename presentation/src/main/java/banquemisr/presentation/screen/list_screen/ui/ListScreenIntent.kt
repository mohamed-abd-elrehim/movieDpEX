package banquemisr.presentation.screen.list_screen.ui

sealed class ListScreenIntent {
    data class  LoadMovies(val isRefreshing: Boolean = false) : ListScreenIntent()
    object DismissErrorDialog: ListScreenIntent()
    object ShowErrorDialog: ListScreenIntent()

}