package banquemisr.data.network.mapper

import banquemisr.data.network.constants.APIKeys
import banquemisr.domain.model.Movie
import com.google.gson.annotations.SerializedName


data class MovieDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>,
    val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class MovieDbResultDTO(
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    val results: List<MovieDTO>
)


    fun MovieDTO.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        posterPath = "${APIKeys.MOVIEDB_IMAGE_URL}${this.posterPath}",
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
//        overview = this.overview,
//        backdropPath = "${APIKeys.MOVIEDB_IMAGE_URL}${this.backdropPath}",
//        voteCount = this.voteCount,
//        genreIds = this.genreIds,
//        popularity = this.popularity,
//        adult = this.adult,
//        originalLanguage = this.originalLanguage,
//        originalTitle = this.originalTitle,
//        video = this.video
    )

}

fun List<MovieDTO>.toMovies(): List<Movie> = map { it.toMovie() }
