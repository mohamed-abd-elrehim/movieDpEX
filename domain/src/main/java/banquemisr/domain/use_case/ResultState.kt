package banquemisr.domain.use_case

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error<out T>(val message: String) : ResultState<T>()
}