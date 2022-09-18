package com.pandorina.domain.model

@kotlinx.serialization.Serializable
data class Price(
    val name: String?,
    val icon: String?,
    val measure: String?,
    val pricePrimary: String?,
    val priceSecondary: String?
)
