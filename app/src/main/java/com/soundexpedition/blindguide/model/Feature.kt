package com.soundexpedition.blindguide.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Feature(
val type: String,
val geometry: Geometry,
val properties: Properties,
) : Parcelable