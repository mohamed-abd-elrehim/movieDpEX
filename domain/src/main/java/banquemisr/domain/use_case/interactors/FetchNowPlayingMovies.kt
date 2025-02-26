package banquemisr.domain.use_case.interactors

import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.IMovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchNowPlayingMovies @Inject constructor(
    private val IMovieRepository: IMovieRepository
) {

    operator fun invoke(): Flow<DomainState<List<MovieDomainModel>>> = flow {

        emit(IMovieRepository.fetchNowPlayingMovies())

    }
}