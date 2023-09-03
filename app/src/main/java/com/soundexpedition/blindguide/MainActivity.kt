package com.soundexpedition.blindguide

import com.soundexpedition.blindguide.model.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import java.io.IOException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // 데이터를 저장할 dataList를 클래스 멤버 변수로 선언
    private val dataList: MutableList<Pair<String, List<Pair<Double, Double>>>> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) { //IO 스레드에서 코루틴 실행
            try {
                val postResponse = performPostRequest()

                if (postResponse.isSuccessful) {
                    val featureCollection: FeatureCollection? = postResponse.body()
                    val features: List<Feature>? = featureCollection?.features

                    features?.forEach { feature ->
                        val geometry: Geometry = feature.geometry
                        val properties: Properties = feature.properties
                        val coordinates: Any? = geometry.coordinates
                        val propertyName: String? = properties.name // propertyName을 nullable로 변경

                        if (propertyName != null) {

                            // 좌표 데이터를 파싱하여 좌표 리스트로 변환
                            val coordinatePairs: List<Pair<Double, Double>> =
                                parseCoordinates(coordinates)

                            // coodinates의 위도와 경도 순서를 바꾸어 데이터를 리스트에 저장
//                        val swappedCoordinates = coordinates?.map { coordinateList ->
//                            coordinateList?.let {
//                                val longitude = it.getOrNull(0) ?: 0.0 // 첫 번째 좌표 (경도)
//                                val latitude = it.getOrNull(1) ?: 0.0 // 두 번째 좌표 (위도)
//                                listOf(latitude, longitude)
//                            }
//                        } ?: emptyList()

                            // 데이터를 리스트에 저장
                            val dataItem: Pair<String, List<Pair<Double, Double>>> =
                                Pair(propertyName, coordinatePairs)
                            dataList.add(dataItem)
                        } else {
                            // propertyName이 null인 경우 처리
                            Log.e("MainActivity", "Null propertyName encountered.")
                        }
                    }

                    // 데이터 리스트 출력
                    Log.d("MainActivity", "Data List:")
                    dataList.forEachIndexed { index, (propertyName, coordinatePairs) ->
                        Log.d("MainActivity", "Item $index - Name: $propertyName, Coordinates: $coordinatePairs")
                    }

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

    // coordinates를 문자열에서 리스트로 처리
    private fun parseCoordinates(coordinates: Any?): List<Pair<Double, Double>> {
        val validCoordinates = mutableListOf<Pair<Double, Double>>()

        if (coordinates is List<*>) {
            for (coordinate in coordinates) {
                if (coordinate is String) { //string이 아니다ㅜㅜ 실행 안됨
                    // 문자열에서 공백과 쉼표를 제거
                    val cleanedCoordinate = coordinate.replace("[\\s,]".toRegex(), "")
                    val coordinateParts = cleanedCoordinate.split(";", ",") // 여기서 세미콜론 또는 쉼표로 분리

                    if (coordinateParts.size >= 2) {
                        val longitudeStr = coordinateParts[0]
                        val latitudeStr = coordinateParts[1]
                        Log.d("MainActivity", "Longitude: $longitudeStr, Latitude: $latitudeStr")

                        if (!longitudeStr.isNullOrEmpty() && !latitudeStr.isNullOrEmpty()) {
                            try {
                                val longitude = longitudeStr.toDouble()
                                val latitude = latitudeStr.toDouble()

                                validCoordinates.add(Pair(longitude, latitude))
                            } catch (e: NumberFormatException) {
                                // 파싱할 수 없는 문자열이 포함된 경우 무시
                                Log.e("MainActivity", "Invalid coordinate: $longitudeStr, $latitudeStr")
                            }
                        } else {
                            // 빈 문자열이 포함된 경우 무시
                            Log.w("MainActivity", "Empty coordinate: $longitudeStr, $latitudeStr")
                        }
                    } else {
                        // 형식이 맞지 않는 경우 무시
                        Log.e("MainActivity", "Invalid coordinate format: $coordinate")
                    }
                }
            }
        }

        return validCoordinates
    }
}