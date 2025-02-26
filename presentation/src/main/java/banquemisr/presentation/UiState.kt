package banquemisr.presentation

sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<out T>(val message: String) : UiState<T>()
}