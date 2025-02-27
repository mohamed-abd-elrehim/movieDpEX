package banquemisr.data.network.repository

import banquemisr.data.network.app_exception.ExceptionHandler
import banquemisr.data.network.data_model.toDomainModel
import banquemisr.data.network.remote.DataState
import banquemisr.data.network.remote.IMovieRemoteDataSource
import banquemisr.domain.domain_model.MovieDetailsDomainModel
import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.IMovieRepository
import banquemisr.domain.use_case.ResultState
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: IMovieRemoteDataSource
) : IMovieRepository {

    override suspend fun fetchUpcomingMovies():
            ResultState<List<MovieDomainModel>> {
        return handleRemoteDataSourceCall(remoteDataSource.fetchUpcomingMovies()) {
            it.results.toDomainModel()
        }
    }

    override suspend fun fetchNowPlayingMovies(): ResultState<List<MovieDomainModel>> {
        return handleRemoteDataSourceCall(remoteDataSource.fetchNowPlayingMovies()) {
            it.results.toDomainModel()
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): ResultState<MovieDetailsDomainModel> {
        return handleRemoteDataSourceCall(remoteDataSource.fetchMovieDetails(movieId)) {
            it.toDomainModel()
        }
    }

}

/*
T → The original API response type (List<MovieDataModel>).
R → The mapped domain model type (List<MovieDomainModel>).
dataState → Represents the result from the remote API call (can be Success or Error).
mapToDomain → A lambda function that transforms T into R.
 */
private inline fun <T, R> handleRemoteDataSourceCall(
    dataState: DataState<T>,
    mapToDomain: (T) -> R
): ResultState<R> {
    return when (dataState) {
        is DataState.Success -> {
            ResultState.Success(mapToDomain(dataState.data))
        }

        is DataState.Error -> {
            val errorMessage = ExceptionHandler.handleException(dataState.exception)
            ResultState.Error(errorMessage)
        }
    }
}