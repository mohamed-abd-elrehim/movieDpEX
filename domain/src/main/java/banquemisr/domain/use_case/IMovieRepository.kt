package banquemisr.domain.use_case

import banquemisr.domain.domain_model.MovieDetailsDomainModel
import banquemisr.domain.domain_model.MovieDomainModel

interface IMovieRepository {
    suspend fun fetchUpcomingMovies(): ResultState<List<MovieDomainModel>>
    suspend fun fetchNowPlayingMovies(): ResultState<List<MovieDomainModel>>
    suspend fun fetchMovieDetails(movieId: Int): ResultState<MovieDetailsDomainModel>

}