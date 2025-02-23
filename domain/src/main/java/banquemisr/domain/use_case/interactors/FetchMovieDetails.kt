package banquemisr.domain.use_case.interactors

import banquemisr.domain.domain_model.MovieDetailsDomainModel
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchMovieDetails @Inject constructor(
    private val movieRepository: MovieRepository

) {
    operator fun invoke(movieId: Int): Flow<DomainState<MovieDetailsDomainModel>> = flow {

        emit(movieRepository.fetchMovieDetails(movieId))

    }
}