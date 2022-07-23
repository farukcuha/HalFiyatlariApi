package com.pandorina.domain.model.dto


data class FirestorePriceDto(val writes: List<Write>)

data class Write(val update: Update)

data class Update(
    val fields: Fields,
    val name: String
)

data class Fields(
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

data class CityId(val stringValue: String)
data class Icon(val stringValue: String)
data class Id(val stringValue: String)
data class LastUpdatedTime(val integerValue: Long)
data class Measure(val stringValue: String)
data class Name(val stringValue: String)
data class PriceDate(val stringValue: String)
data class PricePrimary(val stringValue: String)
data class PriceSecondary(val stringValue: String)