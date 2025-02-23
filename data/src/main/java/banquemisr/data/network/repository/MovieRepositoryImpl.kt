package banquemisr.data.network.repository

import banquemisr.data.network.app_exception.ExceptionHandler
import banquemisr.data.network.data_model.toDomainModel
import banquemisr.data.network.remote.MovieRemoteDataSource
import banquemisr.data.network.remote.DataState
import banquemisr.domain.domain_model.MovieDetailsDomainModel
import banquemisr.domain.domain_model.MovieDomainModel
import banquemisr.domain.use_case.DomainState
import banquemisr.domain.use_case.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override suspend fun fetchUpcomingMovies(
    ): DomainState<List<MovieDomainModel>> {
        return when (val result =
            remoteDataSource.getUpcomingMovies( )) {
            is DataState.Success -> {
                // Map the data to the domain model
                val domainModels = result.data.results.toDomainModel()
                DomainState.Success(domainModels)
            }

            is DataState.Error -> {
                // Handle the exception and return an error state
                val errorMessage = ExceptionHandler.handleException(result.exception)
                DomainState.Error(errorMessage)
            }
        }
        }


    override suspend fun fetchNowPlayingMovies(
    ): DomainState<List<MovieDomainModel>> {
        return when (val result =
            remoteDataSource.getNowPlayingMovies()) {
            is DataState.Success -> {
                // Map the data to the domain model
                val domainModels = result.data.results.toDomainModel()
                DomainState.Success(domainModels)
            }

            is DataState.Error -> {
                // Handle the exception and return an error state
                val errorMessage = ExceptionHandler.handleException(result.exception)
                DomainState.Error(errorMessage)
            }
        }
    }

    override suspend fun fetchMovieDetails(movieId: Int): DomainState<MovieDetailsDomainModel> {
        return when (val result = remoteDataSource.getMovieDetails(movieId)) {
            is DataState.Success -> {
                // Map the data to the domain model
                val domainModel = result.data.toDomainModel()
                DomainState.Success(domainModel)
            }

            is DataState.Error -> {
                // Handle the exception and return an error state
                val errorMessage = ExceptionHandler.handleException(result.exception)
                DomainState.Error(errorMessage)
            }
        }
    }

}
