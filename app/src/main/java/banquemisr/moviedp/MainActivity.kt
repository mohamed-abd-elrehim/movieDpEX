package banquemisr.moviedp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.navigation.compose.rememberNavController
import banquemisr.moviedp.ui.theme.MovieDpTheme
import banquemisr.presentation.navigation.nav_host.MovieNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDpTheme {
                val navController = rememberNavController()
                Box {
                    MovieNavHost(navController = navController)
                }

            }
        }
    }
}
