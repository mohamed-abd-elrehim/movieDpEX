package banquemisr.moviedp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import banquemisr.core.domain.DataState
import banquemisr.domain.use_case.interactors.GetNowPlayingMovies
import banquemisr.moviedp.ui.theme.MovieDpTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getNowPlayingMovies: GetNowPlayingMovies

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieDpTheme {

                    LaunchedEffect(Unit) { // Ensures it only runs when movieId changes


                        getNowPlayingMovies("","").collect { state ->
                            when (state) {
                                is DataState.Data -> {
                                    Log.d("Main", "onCreate: ${state.data}")
                                }
                                is DataState.Loading -> {
                                }
                                is DataState.Response -> {
                                }
                            }
                    }
                }
            }
        }
    }
}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MovieDpTheme {
        Greeting("Android")
    }
}