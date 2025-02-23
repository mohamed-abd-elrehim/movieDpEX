package banquemisr.moviedp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import banquemisr.domain.use_case.MovieRepository
import banquemisr.moviedp.ui.theme.MovieDpTheme
import banquemisr.moviedp.ui.theme.NavControllerHolder
import banquemisr.presentation.screen.details_screen.ui.DetailsScreen
import banquemisr.presentation.screen.details_screen.ui.DetailsScreenViewModel
import banquemisr.presentation.screen.list_screen.ui.ListScreen
import banquemisr.presentation.screen.list_screen.ui.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var movieRepository: MovieRepository


    @Inject lateinit var navControllerHolder: NavControllerHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDpTheme {

                val navController = rememberNavController()
                navControllerHolder.navController = navController

                Box {
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

            }
        }




    }
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
        route = Screen.DetailsScreen.route + "/{movieId}",
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
        DetailsScreen(viewModel = detailsScreenViewModel
        )
    }
}

