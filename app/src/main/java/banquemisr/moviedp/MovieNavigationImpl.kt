package banquemisr.moviedp

import banquemisr.presentation.navigation.MovieNavigation
import javax.inject.Inject

class MovieNavigationImpl @Inject constructor(
    private val holder: NavControllerHolder
): MovieNavigation {
    override fun navigationToDetailsScreen(movieId: Int)
    {
        holder.navController.navigate("${Screen.DetailsScreen.route}/${movieId}")

    }

    override fun navigationBack() {
        holder.navController.popBackStack()

    }

}