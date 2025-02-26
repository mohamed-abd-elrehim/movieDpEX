package banquemisr.domain.use_case

sealed class DomainState<out T> {
    data class Success<out T>(val data: T) : DomainState<T>()
    data class Error<out T>(val message: String) : DomainState<T>()
}