package banquemisr.presentation.screen.details_screen.ui

import banquemisr.domain.domain_model.MovieDetailsDomainModel

data class MovieDetailsUiModel(
    val id: Int,
    val backdropPath: String?,
    val tagline: String?,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String,
    val popularity: Double,
    val runtime: Int?,
    val genres: List<String>,
    val overview: String?,
    val productionCountries: List<String>,
    val productionCompanies: List<Pair<String, String>>,
    val budget: Int,
    val posterPath: String?,
)

fun MovieDetailsDomainModel.toUiModel(): MovieDetailsUiModel {
    return MovieDetailsUiModel(
        id = id,
        backdropPath = backdropPath,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount,
        releaseDate = releaseDate,
        popularity = popularity,
        runtime = runtime,
        genres = genres,
        overview = overview,
        productionCountries = productionCountries,
        productionCompanies = productionCompanies,
        budget = budget,
        posterPath = posterPath,
    )

}