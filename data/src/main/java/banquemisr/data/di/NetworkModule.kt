package banquemisr.data.di

import banquemisr.data.network.api.MovieDbAPIServices
import banquemisr.data.network.constants.APIKeys

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS) //  Set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // Set read timeout
            .writeTimeout(30, TimeUnit.SECONDS) //  Set write timeout
            .apply {
                addInterceptor(loggingInterceptor()) //  Add logging interceptor
                addInterceptor(authInterceptor()) //  Add authentication interceptor
            }
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return  Retrofit.Builder()
            .baseUrl(APIKeys.MOVIEDB_BASE_URL) //  Set the base URL
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



    /*
    *الـ Interceptor هو كود يتم تنفيذه بشكل أوتوماتيكي مع كل طلب (Request) أو استجابة (Response) في الشبكة. يتم استخدامه في Retrofit عبر OkHttp لإضافة وظائف مثل:
    ✅ إضافة Headers (مثل Authorization أو API Key).
    ✅ تسجيل الطلبات والاستجابات (Logging) لتسهيل عملية الـ Debugging.
    ✅ التعامل مع الأخطاء مثل إعادة المحاولة في حال فشل الطلب بسبب مشكلة في الشبكة.
    * */


    /*
    📌 المعلومات التي سيتم طباعتها في Logcat عند تنفيذ أي طلب API:
1️⃣ عنوان URL الخاص بالطلب (URL)
2️⃣ Headers الخاصة بالطلب والاستجابة
3️⃣ جسم الطلب (Body) إذا كان الطلب يحتوي على بيانات
4️⃣ كود الاستجابة (Response Code)
5️⃣ جسم الاستجابة بالكامل (Response Body)
    * */
    // Logging Interceptor to log request & response details
    private fun loggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY // ⬅ Logs full request & response details
        return logging
    }

    // Authentication Interceptor to add API Key to every request
    private fun authInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()
            val movieUrl = original.url.newBuilder()
                .addQueryParameter(APIKeys.APIKey, APIKeys.MOVIEDB_API_KEY) // ⬅ Attach API key
                // to requests
                .build()
            val request = original.newBuilder().url(movieUrl).build()
            chain.proceed(request)
        }
    }


}





