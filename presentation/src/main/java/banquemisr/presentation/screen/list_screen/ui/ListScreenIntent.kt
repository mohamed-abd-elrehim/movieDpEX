package banquemisr.presentation.screen.list_screen.ui

sealed class ListScreenIntent {
    data class LoadMovies(val sortBy: String = "popularity.desc", val certificationCountry:
    String = "EG") : ListScreenIntent()

    object RefreshMovies : ListScreenIntent()  // User pulls to refresh
    data class MovieClicked(val movieId: Int) : ListScreenIntent()  // User clicks a movie (UI event)
    object RemoveHeadMessageFromQueue: ListScreenIntent()

}