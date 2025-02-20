package banquemisr.data.network.api

import banquemisr.data.network.constants.APIKeys
import banquemisr.data.network.mapper.MovieDbResultDTO
import banquemisr.data.network.mapper.MovieDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDbAPIServices {

    @GET(APIKeys.GET_UPCOMING_MOVIES_ENDPOINT)
    suspend fun fetchUpcomingMovies(
        @Query(APIKeys.SORT_BY) sortBy: String,
        @Query(APIKeys.CERTIFICATION_COUNTRY) certificationCountry:String
    ): MovieDbResultDTO

    @GET(APIKeys.GET_NOW_PLAYING_MOVIES_ENDPOINT)
    suspend fun fetchNowPlayingMovies(
        @Query(APIKeys.SORT_BY) sortBy: String,
        @Query(APIKeys.CERTIFICATION_COUNTRY) certificationCountry:String
    ): MovieDbResultDTO

    @GET(APIKeys.GET_MOVIE_DETAILS_ENDPOINT)
    suspend fun fetchMovieDetails(
        @Path(APIKeys.MOVIE_ID_PARAM) movieId: Int,
    ): MovieDetailsDTO


}