package banquemisr.presentation.screen.details_screen.ui


import androidx.lifecycle.ViewModel
import banquemisr.core.util.Logger
import banquemisr.domain.use_case.interactors.FetchMovieDetails
import coil.ImageLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Named


@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val imageLoader: ImageLoader,
    @Named("DetailsScreenLogger") private val logger: Logger,
    private val fetchMovieDetails: FetchMovieDetails,

):ViewModel()
{
    private val _state = MutableStateFlow(DetailsScreenState(imageLoader= imageLoader))
    val state = _state.asStateFlow()

    fun onIntent(intent: DetailsScreenIntent){
        when(intent){
            is DetailsScreenIntent.LoadMovieDetails  -> fetchMovie(intent.movieID)
            is DetailsScreenIntent.RefreshMovieDetails -> onPageRefresh()
            is DetailsScreenIntent.BackButtonClicked -> onEvent(DetailsScreenEvent.NavigateBackToListScreen)
        }
    }

    fun onEvent(event: DetailsScreenEvent){
        when(event){
            is DetailsScreenEvent.NavigateBackToListScreen -> onButtonClicked()
        }
    }
    private  fun fetchMovie(movieId:Int){

    }
    private  fun onPageRefresh(){

    }
    private  fun onButtonClicked(){

    }


}
