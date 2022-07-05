package com.pandorina.domain.model.core

data class PriceSyncResponse(
    val cityId: String,
    val cityName: String,
    val priceDate: String,
    val lastUpdateTime: Long,
)