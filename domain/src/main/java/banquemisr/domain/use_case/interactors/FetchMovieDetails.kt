package banquemisr.domain.use_case.interactors

import banquemisr.domain.domain_model.MovieDetailsDomainModel
import banquemisr.domain.use_case.IMovieRepository
import banquemisr.domain.use_case.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchMovieDetails @Inject constructor(
    private val iMovieRepository: IMovieRepository

) {
    operator fun invoke(movieId: Int): Flow<ResultState<MovieDetailsDomainModel>> = flow {
        emit(iMovieRepository.fetchMovieDetails(movieId))

    }
}