package banquemisr.domain.use_case.interactors

import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchUpcomingMovies@Inject constructor(
    private val movieRepository: MovieRepository
) {

     operator fun invoke(
    ): Flow<DomainState<List<MovieDomainModel>>> = flow {

        emit(movieRepository.fetchUpcomingMovies())

     }

}