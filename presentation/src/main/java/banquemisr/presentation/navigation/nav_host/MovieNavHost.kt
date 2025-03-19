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
import androidx.navigation.toRoute
import banquemisr.presentation.navigation.screen.DetailsScreen
import banquemisr.presentation.navigation.screen.ListScreen
import banquemisr.presentation.screen.details_screen.ui.DetailsScreen
import banquemisr.presentation.screen.list_screen.ui.ListScreen

@Composable
fun MovieNavHost (navController: NavHostController) {



    NavHost(
        navController = navController,
        startDestination = ListScreen,
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
    composable<ListScreen>(
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
            navController.navigate(DetailsScreen(movieId))
        }
        )

    }
}

fun NavGraphBuilder.addDetailsScreen(
    navController: NavHostController
) {
    composable<DetailsScreen>(
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
        val detailsScreen: DetailsScreen = backStackEntry.toRoute()

        // Pass movieId to DetailsScreen
        DetailsScreen(movieId = detailsScreen.movieId, onBackClicked = {
            navController.popBackStack()
        })
    }
}

