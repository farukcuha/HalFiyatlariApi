package com.pandorina.domain.model

@kotlinx.serialization.Serializable
data class PriceFetchResponse(
    val cityId: String?,
    val title: String?,
    val date: String?,
    val size: Int?,
    val prices: List<Price?>
)