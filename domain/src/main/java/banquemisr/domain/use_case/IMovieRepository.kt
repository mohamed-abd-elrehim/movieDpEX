package banquemisr.domain.use_case

import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.domain_model.MovieDetailsDomainModel

interface IMovieRepository {
    suspend fun fetchUpcomingMovies():
            DomainState<List<MovieDomainModel>>
    suspend fun fetchNowPlayingMovies():
            DomainState<List<MovieDomainModel>>
    suspend fun fetchMovieDetails(movieId: Int): DomainState<MovieDetailsDomainModel>

}