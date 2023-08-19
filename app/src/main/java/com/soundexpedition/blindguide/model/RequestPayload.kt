package com.soundexpedition.blindguide.model

data class RequestPayload(
    val startX: Double,         // 출발지 X좌표(경도)
    val startY: Double,         // 출발지 Y좌표(위도)
    val angle: Int?,            // 각도(유효값: 0~359), default=0
    val speed: Int?,            // 진행속도(Km/h), default=0
    val endPoiId: String?,      // 목적지의 POI ID
    val endX: Double?,          // 목적지 X좌표(경도)
    val endY: Double?,          // 목적지 Y좌표(위도)
    val passList: String?,      // 경유지(최대 5곳)
    val reqCoordType: String?,  // 좌표계 유형, default=WGS84GEO
    val startName: String?,     // 출발지 명칭(UTF-8 기반 URL인코딩으로 처리)
    val endName: String?,       // 목적지 명칭(UTF-8 기반 URL인코딩으로 처리)
    val searchOption: Int?,     // 경로 탐색 옵션, default=0
    val resCoordType: String?,  // 응답 좌표계 유형, default=WGS84GEO
    val sort: String?           // 지리정보 개체 정렬 순서, default=index
)