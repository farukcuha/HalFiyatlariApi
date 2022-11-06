package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class DemrePriceRepository() : BasePriceRepository() {

    companion object{
        const val cityId = "demre"
        const val srcUrl = "https://www.batiakdeniztv.com/demre-hal-fiyatlari-s37.html"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table[style=width:333px;] tr")
                    val date = jsoup.select("tr > td > p > span > b").text().replace("-", "")
                    for (i in 0 until elements.size) {
                        val row = elements[i].select("td")
                        val icon = row.getOrNull(0)?.select("img")?.attr("src")
                        val name = row.getOrNull(1)?.text()
                        val price = row.getOrNull(2)?.text()
                        if (name?.isNotEmpty() == true && price?.isNotEmpty() == true)
                        add(
                            JsoupPrice(
                                cityId = cityId,
                                priceDate = date,
                                name = name,
                                icon = icon,
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