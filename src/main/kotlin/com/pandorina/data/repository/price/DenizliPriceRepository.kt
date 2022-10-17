package com.pandorina.data.repository.price

import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.JsoupPrice
import com.pandorina.domain.model.SyncResponse

class DenizliPriceRepository: BasePriceRepository() {

    companion object{
        const val cityId = "denizli"
        const val srcUrl = "https://denizli.bel.tr/Default.aspx?k=halfiyatlari"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("tr.fiyat")
                    val date = jsoup.select("h1.contentbaslik > b").text()
                    for (i in 0 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val lowPrice = row.getOrNull(1)?.text()
                        val highPrice = row.getOrNull(3)?.text()
                        add(
                            JsoupPrice(
                                cityId = cityId,
                                priceDate = date,
                                name = name,
                                icon = null,
                                measure = null,
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