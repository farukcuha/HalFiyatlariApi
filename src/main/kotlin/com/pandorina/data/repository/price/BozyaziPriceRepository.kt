package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.config.CityConfig
import com.pandorina.domain.model.SyncResponse

class BozyaziPriceRepository: BasePriceRepository() {

    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = CityConfig.Bozyazi.srcUrl,
            parseHtml = { _ ->
                emptyList()
            }
        ).invoke().saveToDatabase()
    }
}