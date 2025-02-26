package banquemisr.presentation.screen.list_screen.ui


sealed class ListScreenEvent{
    data class NavigateToMovieDetails(val movieId: Int) : ListScreenEvent() // Navigation event

}