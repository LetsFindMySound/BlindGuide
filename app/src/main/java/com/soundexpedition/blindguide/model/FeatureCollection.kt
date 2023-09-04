package com.soundexpedition.blindguide.model

data class FeatureCollection(
    val type: String,
    val features: List<Feature>
)