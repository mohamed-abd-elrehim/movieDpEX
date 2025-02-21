package banquemisr.core.util.app_exception

// custom exception typically extend Exception
sealed class AppException(message: String) : Exception(message) {

    data class NetworkError(val errorMessage: String = "No internet connection") : AppException(errorMessage)
    data class ApiError(val code: Int, val errorMessage: String) : AppException(errorMessage)
    data class UnexpectedError(val errorMessage: String = "Something went wrong") : AppException(errorMessage)
}