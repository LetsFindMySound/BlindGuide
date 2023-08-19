package com.soundexpedition.blindguide

import com.soundexpedition.blindguide.core.*
import com.soundexpedition.blindguide.model.*
import retrofit2.http.POST
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.Response
import retrofit2.http.FormUrlEncoded

//postman
interface MapApiService {
    //@FormUrlEncoded
    @POST("tmap/routes/pedestrian?version=1")
    suspend fun postResponseInfo(
        @Header("accept") accept: String = "application/json",
        @Header("appKey") appKey: String = Setting.TMAP_KEY,

        //Querystring Parameters
        //@Query("version") version: Int = 1,

        // Request Payload
        @Body requestPayload: RequestPayload
    ): Response<ResponseInfo>
}

//@Header는 Request Header에 TMAP_API의 Key값을 추가한다.
//@Query는 Request시 담을 Parameter들이며 아래와 같이 URL이 구성되며 Request를 보낸다.