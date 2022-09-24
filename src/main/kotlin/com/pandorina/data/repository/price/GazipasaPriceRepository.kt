package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class GazipasaPriceRepository : BasePriceRepository() {

    companion object{
        const val cityId = "gazipasa"
        const val srcUrl = "https://www.guncelfiyatlari.com/gazipasa-hal-fiyatlari"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table > tbody > tr")
                    val date = elements[0].select("p").text()
                    for (i in 2 until elements.size) {
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val icon = row.select("img").attr("data-layzr")
                        val measure = row.getOrNull(1)?.text()
                        val lowPrice = row.getOrNull(2)?.text()
                        val highPrice = row.getOrNull(3)?.text()
                        add(
                            JsoupPrice(
                                cityId = cityId,
                                priceDate = date,
                                name = name,
                                icon = icon,
                                measure = measure,
                                pricePrimary = lowPrice,
                                priceSecondary = highPrice
                            )
                        )
                    }
                }
            }
        ).invoke().saveToDatabase()
    }
}