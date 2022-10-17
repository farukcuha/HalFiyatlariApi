package com.pandorina.data.repository.price

import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.JsoupPrice
import com.pandorina.domain.model.SyncResponse

class AdanaHalFiyatları: BasePriceRepository() {

    companion object{
        const val cityId = "adana"
        const val srcUrl = "https://www.biadana.com/hal-fiyatlari"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table[class=table table-bordered nobetci-table] > tbody > tr")
                    val date = jsoup.select("div.nobetci-eczane-title > h2").text()
                    for (i in 0 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val measure = row.getOrNull(1)?.text()
                        val lowPrice = row.getOrNull(2)?.text()
                        val highPrice = row.getOrNull(3)?.text()
                        add(
                            JsoupPrice(
                                cityId = cityId,
                                priceDate = date,
                                name = name,
                                icon = null,
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