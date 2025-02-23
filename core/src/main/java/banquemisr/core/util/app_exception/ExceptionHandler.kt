package banquemisr.core.util.app_exception

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/*
UnknownHostException --> No internet connection or incorrect domain name.

SocketTimeoutException --> Slow network, request taking too long.

ConnectException --> Failed to connect to server.

SSLException --> SSL certificate issues.

IOException	--> Generic network error.

HttpException
400 Bad Request → Invalid request parameters
401 Unauthorized → Missing/invalid authentication token
403 Forbidden → User doesn’t have permission
404 Not Found → API endpoint doesn’t exist
408 Request Timeout → Server took too long to respond
429 Too Many Requests → API rate limit exceeded
500 Internal Server Error → Server-side failure
503 Service Unavailable → API is temporarily down

*/

object ExceptionHandler {

    fun handleException(e: Throwable): AppException {
        return when (e) {
            is UnknownHostException, is SocketTimeoutException, is ConnectException, is
            SSLException, is IOException -> AppException.NetworkError()

            is HttpException -> {
                val errorMessage = when (e.code()) {
                    400 -> "Bad Request"
                    401 -> "Unauthorized"
                    403 -> "Forbidden"
                    404 -> "Not Found"
                    408 -> "Request Timeout"
                    429 -> "Too Many Requests"
                    500 -> "Internal Server Error"
                    503 -> "Service Unavailable"
                    else -> "Something went wrong"
                }
                AppException.ApiError(e.code(), errorMessage)
            }

            else -> AppException.UnexpectedError()
        }
    }
}
