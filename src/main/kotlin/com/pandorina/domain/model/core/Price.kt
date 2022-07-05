package com.pandorina.domain.model.core

@kotlinx.serialization.Serializable
data class Price(
    val name: String?,
    val icon: String?,
    val measure: String?,
    val price_primary: String?,
    val price_secondary: String?
)