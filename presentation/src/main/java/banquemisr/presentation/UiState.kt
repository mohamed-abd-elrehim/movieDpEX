package banquemisr.presentation

sealed class UiState<out T> {
    data class Loading<out T>(val loadingState: ProgressBarState) : UiState<T>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error<out T>(val message: String) : UiState<T>()
}