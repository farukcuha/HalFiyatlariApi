package com.pandorina.data.local

import com.pandorina.domain.config.*
import com.pandorina.domain.model.dto.PriceDto
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

object PricesDataSource {
    val cities = listOf(
        CityConfig.Kumluca,
        CityConfig.Demre,
        CityConfig.Finike,
        CityConfig.Alanya,
        CityConfig.Serik,
        CityConfig.Gazipasa,
        CityConfig.Antalya,
        CityConfig.Istanbul,
        CityConfig.Bozyazi,
    )

    suspend fun insertPrices(prices: List<PriceDto>): Boolean {
        return false
    }

    suspend fun getPriceDates(cityId: String?): List<String?>{
        return emptyList()
    }

    suspend fun getPricesByDate(cityId: String?, date: String?): List<PriceDto> {
        return emptyList()
    }

    private fun getCityTitleByCityId(cityId: String?): String?{
        return ""
    }

    suspend fun isPriceExist(priceId: String?): Boolean{
        return false
    }

    suspend fun deletePricesByCityId(cityId: String?): Boolean {
        return false
    }

    suspend fun deletePricesByCityAndDate(cityId: String?, date: String?): Boolean {
        return false
    }

    suspend fun deleteAllPrices(){

    }

    /*suspend fun getLastPrices(cityId: String?): PriceFetchResponse {
        return getPricesByDate(cityId, getPriceDates(cityId).firstOrNull())
    }*/
}