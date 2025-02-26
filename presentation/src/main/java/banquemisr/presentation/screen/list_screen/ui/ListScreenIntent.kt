package banquemisr.presentation.screen.list_screen.ui

sealed class ListScreenIntent {
    object  LoadMovies : ListScreenIntent()
    object RefreshMovies : ListScreenIntent()  // User pulls to refresh
    data class MovieClicked(val movieId: Int) : ListScreenIntent()  // User clicks a movie (UI event)

    object DismissUpcomingError : ListScreenIntent()
    object DismissNowPlayingError : ListScreenIntent()
    object ShowUpcomingError : ListScreenIntent()
    object ShowNowPlayingError : ListScreenIntent()

}