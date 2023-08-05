package model

data class Feature(
    val type: String,
    val geometry: Geometry,
    val properties: Properties
)