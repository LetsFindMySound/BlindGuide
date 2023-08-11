package com.soundexpedition.blindguide

import model.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrofit을 사용하여 네트워크 요청 수행
        performNetworkRequest()
    }

    private fun performNetworkRequest() {
        val mapApiService = RetrofitUtil.mapApiService

        // 비동기 처리를 위해 GlobalScope.launch 사용
        GlobalScope.launch(Dispatchers.Main) {
            try {
                // 네트워크 요청 수행
                val response: Response<ResponseInfo> = mapApiService.getResponseInfo()

                if (response.isSuccessful) {
                    val responseInfo: ResponseInfo? = response.body()
                    val features: List<Feature>? = responseInfo?.features

                    // TODO: 필요한 데이터를 features에서 추출하여 처리

                } else {
                    // 실패 처리
                    val errorMessage = "네트워크 요청 실패: ${response.code()} ${response.message()}"
                }

            } catch (e: IOException) {
                // 실패 처리
                val errorMessage = "네트워크 오류: ${e.message}"
            }
        }
    }
}