package banquemisr.data.network.constants


object APIKeys {


    // Init setup
    const val  MOVIEDB_BASE_URL = "https://api.themoviedb.org/3/movie/"
    const val  MOVIEDB_IMAGE_URL = "https://image.tmdb.org/t/p/w500"

    const val MOVIEDB_API_KEY = "7396c8dfd064f9d442ae45269c103055"


    //Endpoints
    const val GET_UPCOMING_MOVIES_ENDPOINT = "upcoming"
    const val GET_NOW_PLAYING_MOVIES_ENDPOINT = "now_playing"
    const val GET_MOVIE_DETAILS_ENDPOINT = "{movie_id}"


    //EndPoints Params
    const val MOVIE_ID_PARAM = "movie_id"
    const val APIKey = "api_key"
    const val SORT_BY = "sort_by"
    const val CERTIFICATION_COUNTRY = "certification_country"

    //EndPoints Params values
    const val SORT_BY_VALUE = "popularity.desc"
    const val CERTIFICATION_COUNTRY_VALUE = "EG"




}



