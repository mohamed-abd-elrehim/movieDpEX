package banquemisr.presentation.screen.list_screen.ui

/*
. Event (One-time effects like navigation, toast messages, etc.)
Used for actions that should happen only once (e.g., navigation, showing a snackbar).
Events are different from State, which persists UI changes
 */
sealed class ListScreenEvent{
    data class NavigateToMovieDetails(val movieId: Int) : ListScreenEvent() // Navigation event

}