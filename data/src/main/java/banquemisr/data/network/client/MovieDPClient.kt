package banquemisr.data.network.client

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.constants.APIKeys
import banquemisr.data.network.interceptors.Interceptors
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit



object MovieDPClient {

    // Lazy initialization of OkHttpClient Using lazy in MovieDPClient improves efficiency and ensures resources are only created when needed.
    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) //  Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
            .writeTimeout(30, TimeUnit.SECONDS) //  Set write timeout
            .apply {
                addInterceptor(Interceptors.loggingInterceptor()) //  Add logging interceptor
                addInterceptor(Interceptors.authInterceptor()) //  Add authentication interceptor
            }
            .build()
    }

    // Lazy initialization of Retrofit
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(APIKeys.MOVIEDB_BASE_URL) //  Set the base URL
            .addConverterFactory(GsonConverterFactory.create()) // Convert JSON responses
            .client(client) // Attach OkHttpClient
            .build()
    }

    // API Service instance (lazy-loaded)
    val apiService: MovieDbAPIServices by lazy {
        retrofit.create(MovieDbAPIServices::class.java) //  Create API service
    }
}


