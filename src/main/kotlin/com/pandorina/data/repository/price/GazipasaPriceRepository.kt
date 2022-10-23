package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class GazipasaPriceRepository : BasePriceRepository() {

    companion object{
        const val cityId = "gazipasa"
        const val srcUrl = "https://gazipasa.bel.tr/hal-fiyatlari-32"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("tr")
                    val date = jsoup.select("span[style=font-size: 28px;]").text()
                    for (i in 0 until elements.size) {
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val measure = row.getOrNull(1)?.text()
                        val price = row.getOrNull(3)?.text()
                        if (name != null && measure != null && price != null)
                        add(
                            JsoupPrice(
                                cityId = cityId,
                                priceDate = date,
                                name = name,
                                icon = null,
                                measure = measure,
                                pricePrimary = price,
                                priceSecondary = null
                            )
                        )
                    }
                    removeAt(0)
                }
            }
        ).invoke().saveToDatabase()
    }
}