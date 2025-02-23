package banquemisr.data.network.data_model

import banquemisr.data.network.constants.APIKeys
import banquemisr.domain.domain_model.MovieDetailsDomainModel
import com.google.gson.annotations.SerializedName
data class MovieDetailsDataModel(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: CollectionInfoDataModel?,
    val budget: Int,
    val genres: List<GenreDataModel>,
    val homepage: String?,
    val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDataModel>,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryDataModel>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguageDataModel>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class CollectionInfoDataModel(
    val id: Int,
    val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)

data class GenreDataModel(
    val id: Int,
    val name: String
)

data class ProductionCompanyDataModel(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class ProductionCountryDataModel(
    @SerializedName("iso_3166_1") val isoCode: String,
    val name: String
)

data class SpokenLanguageDataModel(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val isoCode: String,
    val name: String
)
fun MovieDetailsDataModel.toDomainModel(): MovieDetailsDomainModel {
    return MovieDetailsDomainModel(
        posterPath = "${APIKeys.MOVIEDB_IMAGE_URL}${this.posterPath}",
        backdropPath = "${APIKeys.MOVIEDB_IMAGE_URL}${this.backdropPath}",
        budget = this.budget,
        genres = this.genres.toGenreNames(),
        id = this.id,
        overview = this.overview,
        popularity = this.popularity,
        productionCompanies = this.productionCompanies.toProductionCompanyNames(),
        productionCountries = this.productionCountries.toProductionCountriesNames() ,
        releaseDate = this.releaseDate,
        runtime = this.runtime,
        tagline = this.tagline,
        title = this.title,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}

fun List<GenreDataModel>.toGenreNames(): List<String> {
    return this.map { it.name }
}
fun List<ProductionCountryDataModel>.toProductionCountriesNames(): List<String> {
    return this.map { it.name }
}
fun List<ProductionCompanyDataModel>.toProductionCompanyNames(): List<Pair<String, String>> {
    return this.map { Pair(it.name, "${APIKeys.MOVIEDB_IMAGE_URL}${it.logoPath}") }
}
