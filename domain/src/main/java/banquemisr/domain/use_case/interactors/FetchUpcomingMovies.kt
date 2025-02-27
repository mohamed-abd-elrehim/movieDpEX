package banquemisr.domain.use_case.interactors

import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.IMovieRepository
import banquemisr.domain.use_case.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchUpcomingMovies@Inject constructor(
    private val iMovieRepository: IMovieRepository
) {

     operator fun invoke(
     ): Flow<ResultState<List<MovieDomainModel>>> = flow {

        emit(iMovieRepository.fetchUpcomingMovies())

     }

}