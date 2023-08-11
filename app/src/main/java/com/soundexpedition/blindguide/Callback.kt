package com.soundexpedition.blindguide

import core.*
import okhttp3.*
import java.io.IOException

fun performNetworkRequest() {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url(Setting.TMAP_URL)
        .build()

    client.newCall(request).enqueue(
        object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // 실패 처리
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.peekBody(Long.MAX_VALUE).string()
                    // responseBody 처리
                } else {
                    // 실패 처리
                }
            }
        }
    )
}