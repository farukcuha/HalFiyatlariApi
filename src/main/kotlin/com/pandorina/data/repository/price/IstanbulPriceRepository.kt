package com.pandorina.data.repository.price

import com.pandorina.domain.model.jsoup.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.config.CityConfig
import com.pandorina.domain.model.SyncResponse

class IstanbulPriceRepository: BasePriceRepository() {

    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = CityConfig.Istanbul.srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("tr[style=height: 40px;]")
                    val date = jsoup.select("div > table > thead > tr > td > p").first()?.text()
                    for (i in 0 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val icon = row.select("img").attr("data-layzr")
                        val measure = row.getOrNull(1)?.text()
                        val lowPrice = row.getOrNull(2)?.text()
                        val highPrice = row.getOrNull(3)?.text()
                        add(
                            JsoupPrice(
                                cityId = CityConfig.Istanbul.id,
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