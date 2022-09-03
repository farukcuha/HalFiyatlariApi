package com.pandorina.domain.model

@kotlinx.serialization.Serializable
data class Currency(
    val name: String?,
    val price: String?,
    val changeRate: String?,
    val changeAmount: String?,
    val trendUp: Boolean
)
