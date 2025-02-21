package banquemisr.moviedp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.hilt.navigation.compose.hiltViewModel
import banquemisr.domain.use_case.MovieRepository
import banquemisr.moviedp.ui.theme.MovieDpTheme
import banquemisr.presentation.screen.list_screen.ui.ListScreen
import banquemisr.presentation.screen.list_screen.ui.ListScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var movieRepository: MovieRepository

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
//            statusBarStyle = SystemBarStyle.light(
//                Color.White.toArgb(),
//                Color.White.toArgb() // Light scrim for contrast
//            )
        )
        setContent {
            MovieDpTheme {
                val listScreenViewModel: ListScreenViewModel = hiltViewModel()

                ListScreen(listScreenViewModel)

            }
        }




    }
}