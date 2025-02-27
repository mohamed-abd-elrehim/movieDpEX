package banquemisr.domain.domain_model



data class MovieDomainModel(
    val id: Int,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val title: String,
)
