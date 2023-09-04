package com.soundexpedition.blindguide

import com.soundexpedition.blindguide.core.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object RetrofitUtil {

    private var instance: Retrofit? = null

    val mapApiService: MapApiService by lazy {
        getRetrofit().create(MapApiService::class.java)
    } // Retrofit 초기화

    private fun getRetrofit(): Retrofit {
        if(instance == null) {
            instance = Retrofit.Builder() //객체 생성
                .baseUrl(Setting.TMAP_URL) //통신할 서버 주소 설정
                .addConverterFactory(GsonConverterFactory.create())
                .client(buildOkHttpClient())
                .build()
        }
        return instance!!
    }

    private fun buildOkHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()

        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
    }
}