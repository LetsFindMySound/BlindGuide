package com.soundexpedition.blindguide.model

import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("type") val type: String,
    @SerializedName("geometry") val geometry: Geometry,
    @SerializedName("properties") val properties: Properties
)