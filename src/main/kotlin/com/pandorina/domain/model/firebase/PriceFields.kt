package com.pandorina.domain.model.firebase

data class PriceFields(
    val cityId: CityId,
    val icon: Icon,
    val id: Id,
    val lastUpdatedTime: LastUpdatedTime,
    val measure: Measure,
    val name: Name,
    val priceDate: PriceDate,
    val pricePrimary: PricePrimary,
    val priceSecondary: PriceSecondary
)
