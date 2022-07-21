package com.pandorina.data.repository.price

import com.google.gson.Gson
import com.pandorina.domain.model.jsoup.JsoupPrice
import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.remote.collectJsoupResult
import com.pandorina.domain.model.dto.PriceDto
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

abstract class BasePriceRepository {

    abstract suspend fun syncPrices(): String?

    suspend fun Flow<JsoupResult<List<JsoupPrice>>>.saveToDatabase(): String? {
        var responseMessage: String? = null
        collectJsoupResult(
            onSuccess = { list ->
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
                }?.let {
                    try {
                        val process = insertPrices(it)
                        responseMessage = "${process.status.value} - ${process.status.description}"
                    } catch (e: Exception){
                        e.localizedMessage
                    }
                }
            },
            onFailure = {
                println(it?.localizedMessage)
                responseMessage = "Sync is failed! : ${it?.localizedMessage}"
            }
        )
        return responseMessage
    }

    private fun primaryIdGenerator(jsoupPrice: JsoupPrice?): String {
        return StringBuilder().run {
            append("${jsoupPrice?.cityId}")
            append("${jsoupPrice?.priceDate}")
            append("${jsoupPrice?.name}")
            append("${jsoupPrice?.pricePrimary}")
            append("${jsoupPrice?.priceSecondary}")
            this.toString().lowercase().trim()
        }
    }
    private suspend fun insertPrices(list: List<PriceDto>): HttpResponse {
        val client = HttpClient(CIO)
        return client.put {
            url("https://halfiyatlari-511c9-default-rtdb.firebaseio.com/prices" +
                    "/${list[0].cityId}" +
                    "/${list[0].priceDate?.replace("/", "")}.json")
            contentType(ContentType.Application.Json)
            setBody(Gson().toJson(list))
        }
    }
}