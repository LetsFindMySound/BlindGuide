package com.soundexpedition.blindguide.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Properties(
    val totalDistance: Int? = null,
    val totalTime: Int? = null,
    val index: Int,
    val pointIndex: Int? = null,
    val lineIndex: Int? = null,
    val name: String? = null,
    val description: String,
    val direction: String? = null,
    val distance: Int? = null,
    val time: Int? = null,
    val roadType: Int? = null,
    val categoryRoadType: Int? = null,
    val nearPoiName: String? = null,
    val nearPoiX: String? = null,
    val nearPoiY: String? = null,
    val intersectionName: String? = null,
    val facilityType: String? = null,
    val facilityName: String? = null,
    val turnType: Int? = null,
    val pointType: String? = null,
) : Parcelable