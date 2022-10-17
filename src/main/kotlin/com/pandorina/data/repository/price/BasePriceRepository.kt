package com.pandorina.data.repository.price

import com.pandorina.data.local.city.CitiesDataSource
import com.pandorina.data.local.price.PriceTable
import com.pandorina.data.local.price.PricesDataSource
import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.remote.collectJsoupResult
import com.pandorina.domain.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

abstract class BasePriceRepository {
    abstract suspend fun syncPrices(): SyncResponse?

    suspend fun Flow<JsoupResult<List<JsoupPrice>>>.saveToDatabase(): SyncResponse?{
        var syncResponse: SyncResponse? = null
        collectJsoupResult(
            onSuccess = { list ->
                val date = list?.first()?.priceDate
                try {
                    transaction {
                        val controlId = primaryIdGenerator(list?.firstOrNull())
                        if (PricesDataSource.isPriceExist(controlId)){
                            syncResponse = SyncResponse(
                                statusCode = HttpStatusCode.OK.value,
                                message = "Prices are already up to date : $date"
                            )
                        } else {
                            val time = System.currentTimeMillis()
                            list?.distinctBy {
                                primaryIdGenerator(it)
                            }?.forEach { price ->
                                PriceTable.insert {
                                    it[id] = primaryIdGenerator(price)
                                    it[cityId] = price.cityId
                                    it[priceDate] = price.priceDate
                                    it[lastUpdatedTime] = time
                                    it[name] = price.name
                                    it[icon] = price.icon
                                    it[measure] = price.measure
                                    it[pricePrimary] = price.pricePrimary
                                    it[priceSecondary] = price.priceSecondary
                                }
                                println(primaryIdGenerator(price))
                            }
                            syncResponse = SyncResponse(
                                statusCode = HttpStatusCode.OK.value,
                                message = "Sync is successfully! : $date"
                            )
                        }
                    }
                } catch (e: ExposedSQLException){
                    syncResponse = SyncResponse(
                        statusCode = HttpStatusCode.InternalServerError.value,
                        message = "Sync is failed! : ${e.localizedMessage}"
                    )
                }
            },
            onFailure = {
                syncResponse = SyncResponse(
                    statusCode = HttpStatusCode.InternalServerError.value,
                    message = "Sync is failed! : ${it?.localizedMessage}"
                )
                println(it?.localizedMessage)
            }
        )
        return syncResponse
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