package banquemisr.data.network.remote

import banquemisr.data.network.data_model.MovieDbResultDataModel
import banquemisr.data.network.data_model.MovieDetailsDataModel

interface IMovieRemoteDataSource {

    suspend fun fetchUpcomingMovies(): DataState<MovieDbResultDataModel>
    suspend fun fetchNowPlayingMovies(): DataState<MovieDbResultDataModel>
    suspend fun fetchMovieDetails(movieId: Int): DataState<MovieDetailsDataModel>

}