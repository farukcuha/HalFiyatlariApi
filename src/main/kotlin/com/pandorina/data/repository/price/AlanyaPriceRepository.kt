package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class AlanyaPriceRepository : BasePriceRepository() {

    companion object{
        const val cityId = "alanya"
        const val srcUrl = "https://www.batiakdeniztv.com/alanya-hal-fiyatlari-s47.html"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table[style=width:366px;] tr")
                    val date = jsoup.select("tr > td > p > strong > span > span[style=line-height:115%] > span").text()
                    for (i in 0 until elements.size){
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
                        if (size == 9) break
                    }
                }
            }
        ).invoke().saveToDatabase()
    }
}