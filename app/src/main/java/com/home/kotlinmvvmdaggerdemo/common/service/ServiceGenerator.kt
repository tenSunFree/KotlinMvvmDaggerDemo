package com.home.kotlinmvvmdaggerdemo.common.service

import com.google.gson.Gson
import com.home.kotlinmvvmdaggerdemo.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ServiceGenerator @Inject
constructor(private val gson: Gson) {

    private val timeoutConnect = 30
    private val timeoutRead = 30
    private val contentType = "Content-Type"
    private val contentTypeValue = "application/json"
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private var retrofit: Retrofit? = null

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder()
                .header(contentType, contentTypeValue)
                .method(original.method, original.body)
                .build()
        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor
                        .apply { loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS }
                        .level = HttpLoggingInterceptor.Level.BODY
            }
            return loggingInterceptor
        }

    init {
        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
    }

    fun <S> createService(serviceClass: Class<S>, baseUrl: String): S {
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder()
                .baseUrl(baseUrl).client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit!!.create(serviceClass)
    }
}