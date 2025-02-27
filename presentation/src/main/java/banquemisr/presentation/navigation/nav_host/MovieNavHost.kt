package banquemisr.presentation.navigation.nav_host
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import banquemisr.presentation.navigation.screen.NavigationKeys
import banquemisr.presentation.navigation.screen.Screen
import banquemisr.presentation.screen.details_screen.ui.DetailsScreen
import banquemisr.presentation.screen.list_screen.ui.ListScreen

@Composable
fun MovieNavHost (navController: NavHostController) {
    


    NavHost(
        navController = navController,
        startDestination = Screen.ListScreen.route,
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        builder = {
            addListScreen(
                navController = navController
            )
            addDetailsScreen(
                navController = navController
            )
        }
    )
}

fun NavGraphBuilder.addListScreen(
    navController: NavHostController
) {
    composable(
        route = Screen.ListScreen.route,
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))


        }
    ) {

        ListScreen(onMovieClick = { movieId ->
            navController.navigate(Screen.DetailsScreen.route + "/$movieId")
        }
        )

    }
}

fun NavGraphBuilder.addDetailsScreen(
    navController: NavHostController
) {
    composable(
        route = Screen.DetailsScreen.route + "/{${NavigationKeys.MOVIE_ID}}",
        arguments = Screen.DetailsScreen.arguments,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) { backStackEntry ->
        //must not send view model her due to recomposition
        //val detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel() // this is wrong

        // Fetch MOVIE_ID argument from backStackEntry
        val movieId = backStackEntry.arguments?.getInt(NavigationKeys.MOVIE_ID)

        // Pass movieId to DetailsScreen
        DetailsScreen(movieId = movieId, onBackClicked = {
            navController.popBackStack()
        })
    }
}

