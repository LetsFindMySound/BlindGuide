package com.soundexpedition.blindguide

import core.*
import model.*
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.Response

interface MapApiService {
    @GET(Setting.TMAP_URL)
    suspend fun getResponseInfo(
        @Header("appKey") appKey: String = Setting.TMAP_API,

        //Querystring Parameters
        @Query("version") version: Int = 1,
        @Query("callback") callback: String? = null,

        ): Response<ResponseInfo>

    @POST(Setting.TMAP_URL)
    suspend fun postResponseInfo(
        @Header("appKey") appKey: String = Setting.TMAP_API,

        // Request Payload
        @Body requestPayload: RequestPayload
    ): Response<ResponseInfo>
}

//@GET은 HTTP 통신 방식으로 Parameter들이 URL에 추가되는 GET 방식으로 받아오겠다.
//@Header는 Request Header에 TMAP_API의 Key값을 추가한다.
//@Query는 Request시 담을 Parameter들이며 아래와 같이 URL이 구성되며 Request를 보낸다.