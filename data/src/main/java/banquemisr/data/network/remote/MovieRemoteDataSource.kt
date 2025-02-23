package banquemisr.data.network.remote

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.app_exception.AppException
import banquemisr.data.network.app_exception.toAppException
import banquemisr.data.network.data_model.MovieDbResultDataModel
import banquemisr.data.network.data_model.MovieDetailsDataModel
import retrofit2.Response
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(
    private val apiService: MovieDbAPIServices
) {
    suspend fun getUpcomingMovies(
    ): DataState<MovieDbResultDataModel> {
        return handleApiCall {
            apiService.fetchUpcomingMovies()
        }
    }

    suspend fun getNowPlayingMovies(
    ): DataState<MovieDbResultDataModel> {
        return handleApiCall {
            apiService.fetchNowPlayingMovies()
        }
    }

    suspend fun getMovieDetails(movieId: Int): DataState<MovieDetailsDataModel> {
        return handleApiCall {
            apiService.fetchMovieDetails(movieId)
        }
    }

}




// Generic error wrapper for API failures

private suspend fun <T> handleApiCall(
    apiCall: suspend () -> Response<T>
): DataState<T> {

    return try {
        val response = apiCall()
        if (response.isSuccessful) {
            response.body()?.let {
                DataState.Success(it)
            } ?: DataState.Error(AppException.NullPointerExceptionError())
        } else {
            DataState.Error(AppException.ApiError(response.code(), response.message()))
        }
    } catch (e: Exception) {
        DataState.Error(e.toAppException())
    }
}