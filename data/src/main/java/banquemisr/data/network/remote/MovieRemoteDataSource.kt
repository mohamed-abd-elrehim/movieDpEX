package banquemisr.data.network.remote

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.mapper.MovieDbResultDTO
import banquemisr.data.network.mapper.MovieDetailsDTO
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiService: MovieDbAPIServices
) {
    suspend fun fetchUpcomingMovies(sortBy: String, certificationCountry: String): MovieDbResultDTO =
        apiService.fetchUpcomingMovies(sortBy, certificationCountry)

    suspend fun fetchNowPlayingMovies(sortBy: String, certificationCountry: String): MovieDbResultDTO =
        apiService.fetchNowPlayingMovies(sortBy, certificationCountry)

    suspend fun fetchMovieDetails(movieId: Int): MovieDetailsDTO =
        apiService.fetchMovieDetails(movieId)
}
