package com.pandorina.data.local

import com.pandorina.domain.model.core.Price
import com.pandorina.domain.model.core.PriceFetchResponse
import com.pandorina.domain.config.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

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

    fun getPriceDates(cityId: String?): List<String?>{
        return transaction {
            PriceTable
                .select {
                    PriceTable.cityId eq cityId
                }
                .orderBy(PriceTable.lastUpdatedTime to SortOrder.DESC)
                .map {
                    it[PriceTable.priceDate]
                }.distinct()
        }
    }

    fun getPricesByDate(cityId: String?, date: String?): PriceFetchResponse {
        return transaction {
            val prices = PriceTable.select {
                (PriceTable.cityId eq cityId) and (PriceTable.priceDate eq date)
            }.map {
                it.toPrice()
            }
            PriceFetchResponse(
                cityId = cityId,
                title = getCityTitleByCityId(cityId),
                date = date,
                size = prices.size,
                prices = prices
            )
        }
    }

    private fun getCityTitleByCityId(cityId: String?): String?{
        return cities.find {
            it.id == cityId
        }?.title
    }

    fun isPriceExist(priceId: String?): Boolean{
        return transaction {
            !PriceTable.select {
                PriceTable.id eq priceId
            }.empty()
        }
    }

    fun deletePricesByCityId(cityId: String?){
        transaction {
            PriceTable.deleteWhere {
                PriceTable.cityId eq cityId
            }
        }
    }

    fun deletePricesByCityAndDate(cityId: String?, date: String?){
        transaction {
            PriceTable.deleteWhere {
                PriceTable.cityId eq cityId
                PriceTable.priceDate eq date
            }
        }
    }

    fun deleteAllPrices(){
        transaction {
            PriceTable.deleteAll()
        }
    }

    fun getLastPrices(cityId: String?): PriceFetchResponse {
        return getPricesByDate(cityId, getPriceDates(cityId).firstOrNull())
    }

    private fun ResultRow.toPrice(): Price {
        return Price(
            name = this[PriceTable.name],
            icon = this[PriceTable.icon],
            measure = this[PriceTable.measure],
            pricePrimary = this[PriceTable.pricePrimary],
            priceSecondary = this[PriceTable.priceSecondary],
        )
    }
}