package com.soundexpedition.blindguide

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.soundexpedition.blindguide.model.Feature
import com.soundexpedition.blindguide.model.FeatureCollection
import com.soundexpedition.blindguide.model.Geometry
import com.soundexpedition.blindguide.model.Properties
import org.w3c.dom.Text

class ResponseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_response)
        var intent: Intent = getIntent()
        var response: FeatureCollection? = intent.getParcelableExtra("response")
        Log.d("test", "in the ResponseActivity")
//
//        val uri = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java.classLoader)
//        } else {
//            intent.getParcelableExtra(Intent.EXTRA_STREAM) as? Uri
//        }

        val features: List<Feature>? = response?.features

        features?.forEachIndexed { index, feature ->
            val geometry: Geometry = feature.geometry
            val properties: Properties = feature.properties

            val type: String = geometry.type
            val coordinates: Any? = geometry.coordinates
            val description: String = properties.description

            // 좌표 데이터를 파싱하여 좌표 리스트로 변환
            val coordinateList: List<List<Double>> =
                parseCoordinates(coordinates)


             //데이터 리스트 출력
                        Log.d(
                            "MainActivity", "Data List $index\n" +
                                    "Type: $type,\n" +
                                    "Description: $description,\n" +
                                    "Coordinates: $coordinateList\n"
                        )
        }

        val responseTextView = findViewById<TextView>(R.id.response_view_id)
        responseTextView.text = features.toString()
    }
    // coordinates를 문자열에서 리스트로 처리
    private fun parseCoordinates(coordinates: Any?): List<List<Double>> {
        val validCoordinates = mutableListOf<List<Double>>()

        when (coordinates) {
            is List<*> -> {
                if (coordinates.isNotEmpty() && coordinates[0] is List<*>) {
                    // coordinates가 이중 리스트인 경우 (여러 개의 좌표)
                    for (coordinate in coordinates) {
                        val latitude = (coordinate as List<*>)[1] as? Double
                        val longitude = coordinate[0] as? Double

                        if (latitude != null && longitude != null) {
                            validCoordinates.add(listOf(latitude, longitude))
                        } else {
                            Log.e("MainActivity", "Invalid coordinate format: $coordinate")
                        }
                    }
                } else {
                    // coordinates가 단일 리스트인 경우 (단일 좌표)
                    val latitude = coordinates[1] as? Double
                    val longitude = coordinates[0] as? Double

                    if (latitude != null && longitude != null) {
                        validCoordinates.add(listOf(latitude, longitude))
                    } else {
                        Log.e("MainActivity", "Invalid coordinate format: $coordinates")
                    }
                }
            }
        }
        return validCoordinates
    }
}