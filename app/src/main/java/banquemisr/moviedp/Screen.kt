package banquemisr.moviedp

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen (
    val route:String,
    val arguments:List<NamedNavArgument>
){
    object ListScreen : Screen(
        route = "ListScreen",
        arguments = emptyList()
    )

    object DetailsScreen : Screen(
        route = "DetailsScreen",
        arguments = listOf(
            navArgument("movieId"){
                type = NavType.IntType
            }
        )
    )


}