package banquemisr.presentation.screen.details_screen.ui

import banquemisr.presentation.screen.list_screen.ui.ListScreenIntent

sealed class DetailsScreenIntent {
    data class LoadMovieDetails(val movieID:Int) : DetailsScreenIntent()

    object RefreshMovieDetails : DetailsScreenIntent()
    object BackButtonClicked : DetailsScreenIntent()
    object RemoveHeadMessageFromQueue: DetailsScreenIntent()


}