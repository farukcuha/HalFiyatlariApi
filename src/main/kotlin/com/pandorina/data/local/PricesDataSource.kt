package com.pandorina.data.local

import com.pandorina.domain.model.core.PriceFetchResponse
import com.pandorina.domain.config.*
import com.pandorina.domain.model.dto.PriceDto
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

object PricesDataSource {
    private val client = KMongo.createClient(
        connectionString = "mongodb+srv://farukcuha:Ahmet+2002@halfiyatlaricluster.ugfrb.mongodb.net/?retryWrites=true&w=majority"
    ).coroutine.getDatabase("hal_fiyatlari_database")
    private val collection = client.getCollection<PriceDto>("prices")

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
        return collection
            .insertMany(prices).wasAcknowledged()
    }

    suspend fun getPriceDates(cityId: String?): List<String?>{
        return collection
            .find(PriceDto::cityId eq cityId)
            .descendingSort(PriceDto::lastUpdatedTime)
            .toList()
            .distinctBy {
                it.priceDate
            }.map {
                it.priceDate
            }
    }

    suspend fun getPricesByDate(cityId: String?, date: String?): PriceFetchResponse {
        val prices = collection
            .find(
                PriceDto::cityId eq cityId,
                PriceDto::priceDate eq date
            )
            .toList()
            .map {
                it.toPrice()
            }
        return PriceFetchResponse(
            cityId = cityId,
            title = getCityTitleByCityId(cityId),
            date = date,
            size = prices.size,
            prices = prices
        )
    }

    private fun getCityTitleByCityId(cityId: String?): String?{
        return cities.find {
            it.id == cityId
        }?.title
    }

    suspend fun isPriceExist(priceId: String?): Boolean{
        return collection
            .find(PriceDto::id eq priceId)
            .toList()
            .isNotEmpty()
    }

    suspend fun deletePricesByCityId(cityId: String?): Boolean {
        return collection
            .deleteMany(PriceDto::cityId eq cityId).wasAcknowledged()
    }

    suspend fun deletePricesByCityAndDate(cityId: String?, date: String?): Boolean {
        return collection.deleteMany(
            PriceDto::cityId eq cityId,
            PriceDto::priceDate eq date,
        ).wasAcknowledged()
    }

    suspend fun deleteAllPrices(){
        collection.drop()
    }

    suspend fun getLastPrices(cityId: String?): PriceFetchResponse {
        return getPricesByDate(cityId, getPriceDates(cityId).firstOrNull())
    }
}