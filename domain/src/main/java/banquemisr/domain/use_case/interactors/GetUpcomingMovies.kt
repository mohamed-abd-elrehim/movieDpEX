package banquemisr.domain.use_case.interactors

import banquemisr.core.domain.DataState
import banquemisr.core.domain.ProgressBarState
import banquemisr.core.domain.UIComponent
import banquemisr.domain.model.Movie
import banquemisr.domain.use_case.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUpcomingMovies@Inject constructor(
    private val movieRepository: MovieRepository
) {

    //في Kotlin، الكلمة operator fun invoke تسمح باستدعاء الكائن مباشرةً كما لو كان دالة، بينما suspend تجعل الدالة تعمل داخل الكوروتين (Coroutine).
     operator fun invoke(
        sortBy: String, certificationCountry: String
    ): Flow<DataState<List<Movie>>> = flow {
        emit(DataState.Loading(progressBarState = ProgressBarState.Loading))
        try {
          movieRepository.fetchUpcomingMovies(
                sortBy,
                certificationCountry
            ).collect {
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