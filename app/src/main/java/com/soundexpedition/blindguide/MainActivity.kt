package com.soundexpedition.blindguide

import android.content.Intent
import com.soundexpedition.blindguide.model.*
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineExceptionHandler
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
            throwable.printStackTrace()
        }

        lifecycleScope.launch(Dispatchers.IO + coroutineExceptionHandler) { //IO 스레드에서 코루틴 실행
            try {
                val postResponse = performPostRequest()


                if (postResponse.isSuccessful) {
                    val featureCollection: FeatureCollection? = postResponse.body()

                    val intent = Intent(this@MainActivity, ResponseActivity::class.java)

                    intent.putExtra("response", featureCollection)
                    startActivity(intent)

                } else {
                    // 실패 처리
                    val errorMessage =
                        "POST 요청 실패: ${postResponse.code()} ${postResponse.message()}"
                    Log.e("MainActivity", errorMessage)
                }

            } catch (e: IOException) {
                // 실패 처리
                val errorMessage = "네트워크 오류: ${e.message}"
                Log.e("MainActivity", errorMessage)
            }
        }

    }

    private suspend fun performPostRequest(): Response<FeatureCollection> {
        val mapApiService = RetrofitUtil.mapApiService

        // 임시
        // POST 요청 수행
        val requestPayload = RequestPayload(
            startX = 126.96685297397765,                            // 출발지 X좌표(경도)
            startY = 37.56494740779231,                             // 출발지 Y좌표(위도)
            angle = 0,                                              // 각도(유효값: 0~359), default=0
            speed = 0,                                              // 진행속도(Km/h), default=0
            endPoiId = "10001",                                     // 목적지의 POI ID
            endX = 126.98222462424111,                              // 목적지 X좌표(경도)
            endY = 37.561761195487506,                              //목적지 Y좌표(위도)
            passList = "126.97531539081466,37.55999399938893",      // 경유지(최대 5곳) ex)X1,Y1_X2,Y2_....
            reqCoordType = "WGS84GEO",                              // 좌표계 유형, default=WGS84GEO
            //임시 출발지: 서울 서대문 경찰서
            startName = "%EC%84%9C%EC%9A%B8%20%EC%84%9C%EB%8C%80%EB%AC%B8%20%EA%B2%BD%EC%B0%B0%EC%84%9C",   // 출발지 명칭(UTF-8 기반 URL인코딩으로 처리)
            //임시 도착지: 서울 중앙 우체국
            endName = "%EC%84%9C%EC%9A%B8%20%EC%A4%91%EC%95%99%20%EC%9A%B0%EC%B2%B4%EA%B5%AD",  // 목적지 명칭(UTF-8 기반 URL인코딩으로 처리)
            searchOption = 0,                                       // 경로 탐색 옵션, default=0
            resCoordType = "WGS84GEO",                              // 응답 좌표계 유형, default=WGS84GEO
            sort = "index"                                          // 지리정보 개체 정렬 순서, default=index
        )
        return mapApiService.postResponseInfo(requestPayload = requestPayload)
    }


}