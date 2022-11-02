package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class AntalyaPriceRepository: BasePriceRepository() {
    companion object{
        const val cityId = "antalya"
        const val srcUrl = "https://www.batiakdeniztv.com/antalya-hal-fiyatlari-s39.html"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table[summary=Kumluca Hal Fiyatları] tr")
                    val date = jsoup.select("tr > td > p > span[style=font-size:48px;] > b").text()
                    for (i in 0 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(1)?.text()
                        val price = row.getOrNull(2)?.text()
                        if (name?.isNotEmpty() == true && price?.isNotEmpty() == true)
                        add(
                            JsoupPrice(
                                cityId = cityId,
                                priceDate = date,
                                name = name,
                                icon = null,
                                measure = "Kilogram",
                                pricePrimary = price,
                                priceSecondary = null
                            )
                        )
                    }
                }
            }
        ).invoke().saveToDatabase()
    }
}