package com.soundexpedition.blindguide

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.Response

interface MapApiService {
    @GET(Url.TMAP_URL)
    suspend fun getReverseGeoCode(
        @Header("appKey") appKey: String = Key.TMAP_API,

        @Query("version") version: Int = 1,
        @Query("callback") callback: String? = null,
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("coordType") coordType: String? = null,
        @Query("addressType") addressType: String? = null
    ): Response<Data>
}