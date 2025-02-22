package banquemisr.data.network.mapper

import banquemisr.data.network.constants.APIKeys
import banquemisr.domain.model.CollectionInfo
import banquemisr.domain.model.MovieDetails
import banquemisr.domain.model.SpokenLanguage
import com.google.gson.annotations.SerializedName
data class MovieDetailsDTO(
    val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("belongs_to_collection") val belongsToCollection: CollectionInfoDTO?,
    val budget: Int,
    val genres: List<GenreDTO>,
    val homepage: String?,
    val id: Int,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("origin_country") val originCountry: List<String>,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanyDTO>,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountryDTO>,
    @SerializedName("release_date") val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguageDTO>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
)

data class CollectionInfoDTO(
    val id: Int,
    val name: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?
)

data class GenreDTO(
    val id: Int,
    val name: String
)

data class ProductionCompanyDTO(
    val id: Int,
    @SerializedName("logo_path") val logoPath: String?,
    val name: String,
    @SerializedName("origin_country") val originCountry: String
)

data class ProductionCountryDTO(
    @SerializedName("iso_3166_1") val isoCode: String,
    val name: String
)

data class SpokenLanguageDTO(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("iso_639_1") val isoCode: String,
    val name: String
)
fun MovieDetailsDTO.toMovieDetails(): MovieDetails {
    return MovieDetails(
        adult = this.adult,
        posterPath = "${APIKeys.MOVIEDB_IMAGE_URL}${this.posterPath}",
        backdropPath = "${APIKeys.MOVIEDB_IMAGE_URL}${this.backdropPath}",  belongsToCollection = this.belongsToCollection?.toCollectionInfo(),
        budget = this.budget,
        genres = this.genres.toGenreNames(),
        homepage = this.homepage,
        id = this.id,
        imdbId = this.imdbId,
        originCountry = this.originCountry,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        productionCompanies = this.productionCompanies.toProductionCompanyNames(),


        productionCountries = this.productionCountries.toProductionCountriesNames() ,
        releaseDate = this.releaseDate,
        revenue = this.revenue,
        runtime = this.runtime,
        spokenLanguages = this.spokenLanguages.map { it.toSpokenLanguage() },
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount
    )
}
fun CollectionInfoDTO.toCollectionInfo(): CollectionInfo {
    return CollectionInfo(
        id = this.id,
        name = this.name,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath
    )
}

fun List<GenreDTO>.toGenreNames(): List<String> {
    return this.map { it.name }
}
fun List<ProductionCountryDTO>.toProductionCountriesNames(): List<String> {
    return this.map { it.name }
}
fun List<ProductionCompanyDTO>.toProductionCompanyNames(): List<Pair<String, String>> {
    return this.map { Pair(it.name, "${APIKeys.MOVIEDB_IMAGE_URL}${it.logoPath}") }
}



fun SpokenLanguageDTO.toSpokenLanguage(): SpokenLanguage {
    return SpokenLanguage(
        englishName = this.englishName,
        isoCode = this.isoCode,
        name = this.name
    )
}

