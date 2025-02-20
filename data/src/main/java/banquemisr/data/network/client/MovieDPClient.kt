package banquemisr.data.network.client

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.constants.APIKeys
import banquemisr.data.network.interceptors.Interceptors.authInterceptor
import banquemisr.data.network.interceptors.Interceptors.loggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieDPClient {

    // Create OkHttpClient with custom configurations
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) // ⬅ Set connection timeout
        .readTimeout(30, TimeUnit.SECONDS) // ⬅ Set read timeout
        .writeTimeout(30, TimeUnit.SECONDS) // ⬅ Set write timeout
        .addInterceptor(loggingInterceptor()) // ⬅ Add logging interceptor
        .addInterceptor(authInterceptor())
        .build()

    // Create and configure Retrofit instance
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(APIKeys.MOVIEDB_BASE_URL) // ⬅ Set the base URL for API calls
        .addConverterFactory(GsonConverterFactory.create()) // ⬅ Convert JSON responses to Kotlin objects
        .client(client) // ⬅ Attach OkHttpClient
        .build()

    // Create API service instance
    val apiService: MovieDbAPIServices by lazy {
        retrofit.create(MovieDbAPIServices::class.java) // ⬅ Generate API service implementation
    }
}

