package banquemisr.domain.use_case.interactors

import banquemisr.core.domain.DataState
import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.UIComponent
import banquemisr.domain.model.MovieDetails
import banquemisr.domain.use_case.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetails @Inject constructor(
    private val movieRepository: MovieRepository

) {
    operator fun invoke(movieId: Int): Flow<DataState<MovieDetails>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
        try {
           movieRepository.fetchMovieDetails(movieId).collect {
                emit(DataState.Data(it))
            }
        } catch (e: Exception) {
            emit(
                DataState.Response(
                    uiComponent = UIComponent.Dialog(
                        title = "Error",
                        description = e.message ?: "An unknown error occurred"
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }
}