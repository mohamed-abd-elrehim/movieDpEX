package banquemisr.data.network.api

import banquemisr.data.network.constants.ApiEndPoints
import banquemisr.data.network.data_model.MovieDbResultDataModel
import banquemisr.data.network.data_model.MovieDetailsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbAPIServices {

    @GET(ApiEndPoints.GET_UPCOMING_MOVIES_ENDPOINT)
    suspend fun fetchUpcomingMovies(
        @Query(ApiEndPoints.SORT_BY) sortBy: String = ApiEndPoints.SORT_BY_VALUE,
        @Query(ApiEndPoints.CERTIFICATION_COUNTRY) certificationCountry:String = ApiEndPoints.CERTIFICATION_COUNTRY_VALUE
    ): Response<MovieDbResultDataModel>

    @GET(ApiEndPoints.GET_NOW_PLAYING_MOVIES_ENDPOINT)
    suspend fun fetchNowPlayingMovies(
        @Query(ApiEndPoints.SORT_BY ) sortBy: String = ApiEndPoints.SORT_BY_VALUE,
        @Query(ApiEndPoints.CERTIFICATION_COUNTRY) certificationCountry:String = ApiEndPoints.CERTIFICATION_COUNTRY_VALUE
    ): Response<MovieDbResultDataModel>

    @GET(ApiEndPoints.GET_MOVIE_DETAILS_ENDPOINT)
    suspend fun fetchMovieDetails(
        @Path(ApiEndPoints.MOVIE_ID_PARAM) movieId: Int,
    ): Response<MovieDetailsDataModel>


}
