package com.soundexpedition.blindguide.model

import com.google.gson.annotations.SerializedName

//@SerializedName은 JSON 속성과 객체의 필드를 연결
data class ResponseInfo(
    @SerializedName("type") val type: String,
    @SerializedName("features") val features: List<Feature>
)