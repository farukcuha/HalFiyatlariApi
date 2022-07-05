package com.pandorina.data.repository.price

import com.pandorina.domain.model.jsoup.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.config.bozyaziConfig

class BozyaziPriceRepository: BasePriceRepository() {

    override suspend fun syncPrices(): String? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = bozyaziConfig.srcUrl,
            parseHtml = { _ ->
                emptyList()
            }
        ).invoke().saveToDatabase()
    }
}