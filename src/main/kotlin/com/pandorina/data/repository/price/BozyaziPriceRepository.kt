package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class BozyaziPriceRepository: BasePriceRepository() {

    companion object{
        const val cityId = "bozyazi"
        const val srcUrl = ""
    }

    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = cityId,
            parseHtml = { _ ->
                emptyList()
            }
        ).invoke().saveToDatabase()
    }
}