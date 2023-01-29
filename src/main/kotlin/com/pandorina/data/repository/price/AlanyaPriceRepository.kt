package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class AlanyaPriceRepository : BasePriceRepository() {

    companion object{
        const val cityId = "alanya"
        const val srcUrl = "https://www.batiakdeniztv.com/alanya-hal-fiyatlari"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table")[0]?.select("tr")
                    val date = jsoup.select("div > span > strong")[0].text()
                    elements?.forEach { element ->
                        val row = element.select("td")
                        val icon = row.getOrNull(0)?.select("img")?.attr("src")
                        val name = row.getOrNull(1)?.text()
                        val price = row.getOrNull(2)?.text()
                        if (icon?.isNotBlank() == true && name?.isNotBlank() == true && price?.isNotBlank() == true)
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