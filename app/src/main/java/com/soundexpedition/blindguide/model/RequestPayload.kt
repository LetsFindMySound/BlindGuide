package model

data class RequestPayload(
    val startX: Double,
    val startY: Double,
    val angle: Int?,
    val speed: Int?,
    val endPoiId: String?,
    val endX: Double?,
    val endY: Double?,
    val passList: String?,
    val reqCoordType: String?,
    val startName: String?,
    val endName: String?,
    val searchOption: Int?,
    val resCoordType: String?,
    val sort: String?
)