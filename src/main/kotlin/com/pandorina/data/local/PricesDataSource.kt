package com.pandorina.data.local

import com.pandorina.data.local.CitiesDataSource.cities
import com.pandorina.data.local.PriceTable.cityId
import com.pandorina.domain.model.Price
import com.pandorina.domain.model.PriceFetchResponse
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

object PricesDataSource {

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
                PriceTable.id eq priceId!!
            }.empty()
        }
    }

    fun deletePricesByDays(dayCount: Long){
        transaction {
            val targetTime = LocalDateTime.of(
                LocalDate.now().minusDays(dayCount),
                LocalTime.of(0, 0)
            ).toInstant(ZoneOffset.UTC).toEpochMilli()

            PriceTable.deleteWhere {
                PriceTable.lastUpdatedTime less targetTime
            }
        }
    }

    fun deleteAllPrices(){
        transaction {
            PriceTable.deleteAll()
        }
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