package com.soundexpedition.blindguide.model

import com.google.gson.annotations.SerializedName

data class Properties(
    @SerializedName("index") val index: Int,
    @SerializedName("pointIndex") val pointIndex: Int? = null,
    @SerializedName("lineIndex") val lineIndex: Int? = null,
    @SerializedName("name") val name: String,
    @SerializedName("guidePointName") val guidePointName: String,
    @SerializedName("description") val description: String,
    @SerializedName("direction") val direction: String,
    @SerializedName("intersectionName") val intersectionName: String,
    @SerializedName("nearPoiName") val nearPoiName: String,
    @SerializedName("nearPoiX") val nearPoiX: String,
    @SerializedName("nearPoiY") val nearPoiY: String,
    @SerializedName("crossName") val crossName: String,
    @SerializedName("turnType") val turnType: Int,
    @SerializedName("pointType") val pointType: String,
    @SerializedName("roadName") val roadName: String? = null,
    @SerializedName("distance") val distance: Int? = null,
    @SerializedName("time") val time: Int? = null,
    @SerializedName("roadType") val roadType: Int? = null,
    @SerializedName("categoryRoadType") val categoryRoadType: Int? = null,
    @SerializedName("facilityType") val facilityType: Int? = null,
    @SerializedName("facilityName") val facilityName: String? = null
)