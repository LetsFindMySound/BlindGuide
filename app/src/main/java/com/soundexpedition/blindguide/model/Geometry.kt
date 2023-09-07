package com.soundexpedition.blindguide.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class Geometry(
    val type: String,
    val coordinates: @RawValue Any?
) : Parcelable