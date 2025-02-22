package banquemisr.presentation.navigation

import javax.inject.Inject

class NavToDetailsScreen @Inject constructor(
    private val movieNavigation: MovieNavigation

) {

    operator fun invoke(movieId: Int) {
        movieNavigation.navigationToDetailsScreen(movieId)
    }

}