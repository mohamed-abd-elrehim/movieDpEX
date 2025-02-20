package banquemisr.data.network.interceptors

import banquemisr.data.network.constants.APIKeys
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

/*
*الـ Interceptor هو كود يتم تنفيذه بشكل أوتوماتيكي مع كل طلب (Request) أو استجابة (Response) في الشبكة. يتم استخدامه في Retrofit عبر OkHttp لإضافة وظائف مثل:
✅ إضافة Headers (مثل Authorization أو API Key).
✅ تسجيل الطلبات والاستجابات (Logging) لتسهيل عملية الـ Debugging.
✅ التعامل مع الأخطاء مثل إعادة المحاولة في حال فشل الطلب بسبب مشكلة في الشبكة.
* */
object Interceptors {

    /*
    📌 المعلومات التي سيتم طباعتها في Logcat عند تنفيذ أي طلب API:
1️⃣ عنوان URL الخاص بالطلب (URL)
2️⃣ Headers الخاصة بالطلب والاستجابة
3️⃣ جسم الطلب (Body) إذا كان الطلب يحتوي على بيانات
4️⃣ كود الاستجابة (Response Code)
5️⃣ جسم الاستجابة بالكامل (Response Body)
    * */
    // Logging Interceptor to log request & response details
    fun loggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY // ⬅ Logs full request & response details
        return logging
    }

    // Authentication Interceptor to add API Key to every request
     fun authInterceptor(): Interceptor {
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

