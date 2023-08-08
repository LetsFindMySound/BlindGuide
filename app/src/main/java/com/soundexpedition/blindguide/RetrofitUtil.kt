package com.soundexpedition.blindguide

import core.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object RetrofitUtil {

    private var instance: Retrofit? = null

    // Retrofit 서비스 인스턴스 생성
    val mapApiService: MapApiService by lazy {
        getRetrofit().create(MapApiService::class.java)
    }

    //Retrofit을 초기화
    private fun getRetrofit(): Retrofit {
        if(instance == null) {
            Retrofit.Builder() //객체를 생성해 줍니다.
                .baseUrl(Setting.TMAP_URL) //통신할 서버 주소를 설정합니다.
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