package banquemisr.data.network.repository

import banquemisr.data.network.constants.APIKeys
import banquemisr.data.network.mapper.toMovieDetails
import banquemisr.data.network.mapper.toMovies
import banquemisr.data.network.remote.MovieRemoteDataSource
import banquemisr.domain.model.Movie
import banquemisr.domain.model.MovieDetails
import banquemisr.domain.use_case.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun fetchUpcomingMovies(
        sortBy: String,
        certificationCountry: String
    ): Flow<List<Movie>> {
        val movieDbResultDTO = remoteDataSource.fetchUpcomingMovies(APIKeys.SORT_BY_VALUE, APIKeys.CERTIFICATION_COUNTRY_VALUE)
        return flow {
            val movies = movieDbResultDTO.results.toMovies()
            emit(movies)
        }
    }

    override suspend fun fetchNowPlayingMovies(
        sortBy: String,
        certificationCountry: String
    ): Flow<List<Movie>> {
        val movieDbResultDTO = remoteDataSource.fetchNowPlayingMovies(APIKeys.SORT_BY_VALUE, APIKeys.CERTIFICATION_COUNTRY_VALUE)
        return flow {
            val movies = movieDbResultDTO.results.toMovies()
            emit(movies)
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): Flow<MovieDetails> {
        val movieDetailsDTO = remoteDataSource.fetchMovieDetails(movieId)
        return flow {
            val movieDetails = movieDetailsDTO.toMovieDetails()
            emit(movieDetails)
        }
    }

}
