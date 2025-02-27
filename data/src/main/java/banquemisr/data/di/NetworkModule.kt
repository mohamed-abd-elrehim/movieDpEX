package banquemisr.data.di

import banquemisr.data.BuildConfig
import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.constants.ApiEndPoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient(
        movieDbInterceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) //  Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
            .writeTimeout(30, TimeUnit.SECONDS) //  Set write timeout
            .apply {
                addInterceptor(loggingInterceptor) //  Add logging interceptor
                addInterceptor(movieDbInterceptor) //  Add authentication interceptor
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(BuildConfig.MOVIEDB_BASE_URL) //  Set the base URL
            .addConverterFactory(GsonConverterFactory.create()) // Convert JSON responses
            .client(okHttpClient) // Attach OkHttpClient
            .build()
    }

    // API Service
    @Provides
    @Singleton
    fun provideMovieDbAPIService(retrofit: Retrofit): MovieDbAPIServices {
        return retrofit.create(MovieDbAPIServices::class.java)
    }


    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideMovieDbInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val movieUrl = original.url.newBuilder()
                .addQueryParameter(ApiEndPoints.API_KEY, BuildConfig.MOVIEDB_API_KEY)
                .build()
            val request = original.newBuilder().url(movieUrl).build()
            chain.proceed(request)

        }
    }

}





