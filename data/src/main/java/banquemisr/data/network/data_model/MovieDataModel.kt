package banquemisr.data.network.data_model

import banquemisr.data.BuildConfig
import banquemisr.domain.domain_model.MovieDomainModel
import com.google.gson.annotations.SerializedName


data class MovieDataModel(
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

data class MovieDbResultDataModel(
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int,
    val results: List<MovieDataModel>
)


    fun MovieDataModel.toDomainModel(): MovieDomainModel {
    return MovieDomainModel(
        id = this.id,
        title = this.title,
        posterPath = "${BuildConfig.MOVIEDB_IMAGE_URL}${this.posterPath}",
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage
    )

}

fun List<MovieDataModel>.toDomainModel(): List<MovieDomainModel> = map { it.toDomainModel() }
