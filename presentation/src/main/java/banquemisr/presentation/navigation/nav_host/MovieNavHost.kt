package banquemisr.presentation.navigation.nav_host
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import banquemisr.presentation.navigation.NavControllerHolder
import banquemisr.presentation.navigation.screen.NavigationKeys
import banquemisr.presentation.navigation.screen.Screen
import banquemisr.presentation.screen.details_screen.ui.DetailsScreen
import banquemisr.presentation.screen.details_screen.ui.DetailsScreenViewModel
import banquemisr.presentation.screen.list_screen.ui.ListScreen
import banquemisr.presentation.screen.list_screen.ui.ListScreenViewModel

@Composable
fun MovieNavHost (navController: NavHostController) {
    
    LaunchedEffect(Unit) {
        NavControllerHolder.navController = navController
    }


    NavHost(
        navController = navController,
        startDestination = Screen.ListScreen.route,
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        builder = {
            addListScreen()
            addDetailsScreen()
        }
    )
}

fun NavGraphBuilder.addListScreen(
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
        val listScreenViewModel: ListScreenViewModel = hiltViewModel()

        ListScreen(
            viewModel = listScreenViewModel,
        )

    }
}

fun NavGraphBuilder.addDetailsScreen(
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
    ) {
        val detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
        DetailsScreen(
            viewModel = detailsScreenViewModel
        )
    }
}

