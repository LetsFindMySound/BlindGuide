package com.soundexpedition.blindguide.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeatureCollection(
    val type: String,
    val features: List<Feature>
) : Parcelable