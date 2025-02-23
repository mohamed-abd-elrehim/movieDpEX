package banquemisr.domain.use_case

import banquemisr.domain.model.Movie
import banquemisr.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun fetchUpcomingMovies(sortBy: String, certificationCountry: String): Flow<List<Movie>>
    suspend fun fetchNowPlayingMovies(sortBy: String, certificationCountry: String): Flow<List<Movie>>
    suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails>

}