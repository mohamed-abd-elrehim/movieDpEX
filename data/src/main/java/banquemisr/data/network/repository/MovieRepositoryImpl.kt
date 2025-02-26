package banquemisr.data.network.repository

import banquemisr.data.network.app_exception.ExceptionHandler
import banquemisr.data.network.data_model.toDomainModel
import banquemisr.data.network.remote.MovieRemoteDataSourceImpl
import banquemisr.data.network.remote.DataState
import banquemisr.domain.domain_model.MovieDetailsDomainModel
import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.IMovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSourceImpl
) : IMovieRepository {

    override suspend fun fetchUpcomingMovies(): DomainState<List<MovieDomainModel>> {
        return handleRemoteDataSourceCall(remoteDataSource.fetchUpcomingMovies()) {
            it.results.toDomainModel()
        }
    }

    override suspend fun fetchNowPlayingMovies(): DomainState<List<MovieDomainModel>> {
        return handleRemoteDataSourceCall(remoteDataSource.fetchNowPlayingMovies()) {
            it.results.toDomainModel()
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): DomainState<MovieDetailsDomainModel> {
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
): DomainState<R> {
    return when (dataState) {
        is DataState.Success -> {
            DomainState.Success(mapToDomain(dataState.data))
        }

        is DataState.Error -> {
            val errorMessage = ExceptionHandler.handleException(dataState.exception)
            DomainState.Error(errorMessage)
        }
    }
}