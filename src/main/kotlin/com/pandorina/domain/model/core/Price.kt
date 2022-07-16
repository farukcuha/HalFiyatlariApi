package com.pandorina.domain.model.core

@kotlinx.serialization.Serializable
data class Price(
    val name: String?,
    val icon: String?,
    val measure: String?,
    val pricePrimary: String?,
    val priceSecondary: String?
)