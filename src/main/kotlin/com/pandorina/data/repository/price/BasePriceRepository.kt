package com.pandorina.data.repository.price

import com.pandorina.data.local.PriceTable
import com.pandorina.data.local.PricesDataSource
import com.pandorina.domain.model.jsoup.JsoupPrice
import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.remote.collectJsoupResult
import com.pandorina.domain.model.dto.PriceDto
import kotlinx.coroutines.flow.Flow
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BasePriceRepository {

    abstract suspend fun syncPrices(): String?

    suspend fun Flow<JsoupResult<List<JsoupPrice>>>.saveToDatabase(): String?{
        var responseMessage: String? = null
        collectJsoupResult(
            onSuccess = { list ->
                val date = list?.first()?.priceDate
                try {
                    val controlId = primaryIdGenerator(list?.firstOrNull())
                    if (PricesDataSource.isPriceExist(controlId)){
                        responseMessage = "Prices are already up to date : $date"
                    } else {
                        val time = System.currentTimeMillis()
                        list?.map {
                            PriceDto(
                                id = primaryIdGenerator(it),
                                cityId = it.cityId,
                                priceDate = it.priceDate,
                                lastUpdatedTime = time,
                                name = it.name,
                                icon = it.icon,
                                measure = it.measure,
                                pricePrimary = it.pricePrimary,
                                priceSecondary = it.priceSecondary
                            )
                        }?.let { prices ->
                            responseMessage = if (PricesDataSource.insertPrices(prices)){
                                "Sync is successfully! : $date"
                            } else{
                                "Sync is failed!"
                            }
                        }
                    }
                } catch (e: ExposedSQLException){
                    responseMessage = "Sync is failed! : ${e.localizedMessage}"
                }
            },
            onFailure = {
                println(it?.localizedMessage)
                responseMessage = "Sync is failed! : ${it?.localizedMessage}"
            }
        )
        return responseMessage
    }

    private fun primaryIdGenerator(jsoupPrice: JsoupPrice?): String{
        return StringBuilder().run {
            append("${jsoupPrice?.cityId} - ")
            append("${jsoupPrice?.priceDate} - ")
            append("${jsoupPrice?.name} - ")
            append("${jsoupPrice?.pricePrimary} - ")
            append("${jsoupPrice?.priceSecondary}")
            this.toString()
        }
    }
}