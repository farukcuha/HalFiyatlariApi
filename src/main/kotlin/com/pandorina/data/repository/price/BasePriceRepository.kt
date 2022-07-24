package com.pandorina.data.repository.price

import com.google.gson.Gson
import com.pandorina.domain.model.jsoup.JsoupPrice
import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.remote.collectJsoupResult
import com.pandorina.domain.model.SyncResponse
import com.pandorina.domain.model.dto.*
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import org.jetbrains.exposed.sql.*

abstract class BasePriceRepository {
    companion object {
        const val FIRESTORE_DATABASE_ID = "halfiyatlari-511c9"
    }

    abstract suspend fun syncPrices(): SyncResponse?

    suspend fun Flow<JsoupResult<List<JsoupPrice>>>.saveToDatabase(): SyncResponse? {
        var syncResponse: SyncResponse? = null
        collectJsoupResult(
            onSuccess = { list ->
                val time = System.currentTimeMillis()
                list?.map {
                    PriceDto(
                        id = getPricePrimaryId(it),
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
                    val httpResponse = insertPrices(prices)
                    syncResponse = SyncResponse(
                        statusCode = httpResponse.status.value,
                        message = httpResponse.body()
                    )
                }
            },
            onFailure = {
                syncResponse = SyncResponse(
                    statusCode = HttpStatusCode.InternalServerError.value,
                    message = it?.localizedMessage.toString()
                )
            }
        )
        return syncResponse
    }

    private fun getPricePrimaryId(jsoupPrice: JsoupPrice?): String {
        return StringBuilder().run {
            append("${jsoupPrice?.cityId} - ")
            append("${jsoupPrice?.priceDate} - ")
            append("${jsoupPrice?.name} - ")
            append("${jsoupPrice?.pricePrimary} - ")
            append("${jsoupPrice?.priceSecondary}")
            this.toString().trim().lowercase()
                .replace("/", "")
                .replace("-", "")
                .replace(" ", "")
        }
    }

    private suspend fun insertPrices(list: List<PriceDto>): HttpResponse {
        val client = HttpClient(CIO)
        val prices = list.map {
            Write(
                update = Update(
                    fields = Fields(
                        cityId = CityId(it.cityId ?: ""),
                        icon = Icon(it.icon ?: ""),
                        id = Id(it.id ?: ""),
                        lastUpdatedTime = LastUpdatedTime(it.lastUpdatedTime ?: -1),
                        measure = Measure(it.measure ?: ""),
                        name = Name(it.name ?: ""),
                        priceDate = PriceDate(it.priceDate ?: ""),
                        pricePrimary = PricePrimary(it.pricePrimary ?: ""),
                        priceSecondary = PriceSecondary(it.priceSecondary ?: "")
                    ),
                    name = "projects/$FIRESTORE_DATABASE_ID/databases/(default)/documents/prices/${it.id}"
                )
            )
        }
        return client.post {
            url("https://firestore.googleapis.com/v1beta1/projects/$FIRESTORE_DATABASE_ID/databases/(default)/documents:commit")
            contentType(ContentType.Application.Json)
            setBody(Gson().toJson(FirestorePriceDto(writes = prices)))
        }
    }
}