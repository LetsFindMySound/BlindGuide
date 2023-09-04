package com.soundexpedition.blindguide.model

data class Feature(
    val type: String,
    val geometry: Geometry,
    val properties: Properties,
)