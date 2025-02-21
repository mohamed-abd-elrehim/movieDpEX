package banquemisr.domain.use_case.interactors

import banquemisr.core.domain.DataState
import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.UIComponent
import banquemisr.core.util.app_exception.ExceptionHandler
import banquemisr.domain.model.MovieDetails
import banquemisr.domain.use_case.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchMovieDetails @Inject constructor(
    private val movieRepository: MovieRepository

) {
    operator fun invoke(movieId: Int): Flow<DataState<MovieDetails>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
        try {
           movieRepository.fetchMovieDetails(movieId).collect { data->
                emit(DataState.Data(data))
            }
        } catch (e: Exception) {
            val exception = ExceptionHandler.handleException(e)
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = exception.message
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}