package banquemisr.presentation.navigation.screen

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen (
    val route:String,
    val arguments:List<NamedNavArgument>
){
    object ListScreen : Screen(
        route = NavigationRoutes.LIST_SCREEN,
        arguments = emptyList()
    )

    object DetailsScreen : Screen(
        route = NavigationRoutes.DETAILS_SCREEN,
        arguments = listOf(
            navArgument(NavigationKeys.MOVIE_ID){
                type = NavType.IntType
            }
        )
    )


}