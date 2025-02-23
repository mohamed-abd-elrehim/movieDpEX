
package banquemisr.domain.model

data class MovieDetails(
//    val adult: Boolean,
//    val belongsToCollection: CollectionInfo?,
//    val originalTitle: String,
//    val originalLanguage: String,
//    val revenue: Long,
//    val video: Boolean,
//    val status: String,
//    val imdbId: String?,
//    val homepage: String?,
//    val originCountry: List<String>,
//    val spokenLanguages: List<SpokenLanguage>,



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

//data class CollectionInfo(
//    val id: Int,
//    val name: String,
//    val posterPath: String?,
//    val backdropPath: String?
//)
//
//
//
//
//
//
//data class SpokenLanguage(
//    val englishName: String,
//    val isoCode: String,
//    val name: String
//)