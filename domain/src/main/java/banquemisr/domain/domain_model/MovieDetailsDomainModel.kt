package banquemisr.domain.domain_model

data class MovieDetailsDomainModel(
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