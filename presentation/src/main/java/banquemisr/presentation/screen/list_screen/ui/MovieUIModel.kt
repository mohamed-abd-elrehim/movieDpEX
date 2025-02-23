package banquemisr.presentation.screen.list_screen.ui

import banquemisr.domain.domain_model.MovieDomainModel


data class MovieUiModel(
    val id: Int,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val title: String,
)

fun MovieDomainModel.toUiModel(): MovieUiModel {
    return MovieUiModel(
        id = id,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        title = title,
    )
}

fun List<MovieDomainModel>.toUiModel(): List<MovieUiModel> {
    return map { it.toUiModel() }

}