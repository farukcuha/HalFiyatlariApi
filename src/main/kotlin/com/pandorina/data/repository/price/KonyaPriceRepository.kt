package com.pandorina.data.repository.price

import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.JsoupPrice
import com.pandorina.domain.model.SyncResponse

class KonyaPriceRepository: BasePriceRepository() {

    companion object{
        const val cityId = "konya"
        const val srcUrl = "https://www.halfiyatlari.net/konya-hal-fiyatlari"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("tr[style=border-bottom:1px solid #dcdcdc]")
                    val date = jsoup.select("span[style=font-size: 24px;  margin-top: 4px; font-weight:700]").text()
                    for (i in 0 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val lowPrice = row.getOrNull(1)?.text()
                        val highPrice = row.getOrNull(2)?.text()
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