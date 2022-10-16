package com.pandorina.data.local.price

import com.pandorina.domain.model.Price
import com.pandorina.domain.model.PriceFetchResponse
import org.jetbrains.exposed.sql.*
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
                date = date,
                size = prices.size,
                prices = prices
            )
        }
    }


    fun isPriceExist(priceId: String?): Boolean{
        return transaction {
            !PriceTable.select {
                PriceTable.id eq priceId
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