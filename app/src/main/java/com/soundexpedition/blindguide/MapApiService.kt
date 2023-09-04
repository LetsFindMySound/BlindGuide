package com.soundexpedition.blindguide

import com.soundexpedition.blindguide.core.*
import com.soundexpedition.blindguide.model.*
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Body
import retrofit2.Response

interface MapApiService {
    @POST("tmap/routes/pedestrian?version=1")
    suspend fun postResponseInfo(
        @Header("accept") accept: String = "application/json",
        @Header("appKey") appKey: String = Setting.TMAP_KEY,

        // Request Payload
        @Body requestPayload: RequestPayload
    ): Response<FeatureCollection>
}