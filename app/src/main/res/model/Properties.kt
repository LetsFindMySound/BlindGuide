package model

data class Properties(
    val index: Int,
    val pointIndex: Int? = null,
    val lineIndex: Int? = null,
    val name: String,
    val guidePointName: String,
    val description: String,
    val direction: String,
    val intersectionName: String,
    val nearPoiName: String,
    val nearPoiX: String,
    val nearPoiY: String,
    val crossName: String,
    val turnType: Int,
    val pointType: String,
    val roadName: String? = null,
    val distance: Int? = null,
    val time: Int? = null,
    val roadType: Int? = null,
    val categoryRoadType: Int? = null,
    val facilityType: Int? = null,
    val facilityName: String? = null
)