package com.pandorina.domain.model.dto

import com.pandorina.domain.model.core.Price

@kotlinx.serialization.Serializable
data class PriceDto(
    val id: String?,
    val cityId: String?,
    val priceDate: String?,
    val lastUpdatedTime: Long?,
    val name: String?,
    val icon: String?,
    val measure: String?,
    val pricePrimary: String?,
    val priceSecondary: String?
){
    fun toPrice(): Price{
        return Price(
            name = name,
            icon = icon,
            measure = measure,
            pricePrimary = pricePrimary,
            priceSecondary = priceSecondary
        )
    }
}
