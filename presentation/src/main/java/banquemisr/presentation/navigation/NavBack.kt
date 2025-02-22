package banquemisr.presentation.navigation

import javax.inject.Inject

class NavBack @Inject constructor(
    private val movieNavigation: MovieNavigation

) {

   operator fun invoke() {
        movieNavigation.navigationBack()

   }

}