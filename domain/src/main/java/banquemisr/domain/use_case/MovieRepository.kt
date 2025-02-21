package banquemisr.domain.use_case

import banquemisr.core.domain.DataState
import banquemisr.domain.model.Movie
import banquemisr.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getUpcomingMovies(sortBy: String, certificationCountry: String): Flow<List<Movie>>
    suspend fun getNowPlayingMovies(sortBy: String, certificationCountry: String): Flow<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetails>

}