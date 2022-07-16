package com.pandorina.domain.model.jsoup

import kotlinx.serialization.Serializable

data class JsoupPrice(
    val cityId: String? = null,
    val priceDate: String? = null,
    val lastUpdatedTime: Long? = null,
    val name: String? = null,
    val icon: String? = null,
    val measure: String? = null,
    val pricePrimary: String? = null,
    val priceSecondary: String? = null
)