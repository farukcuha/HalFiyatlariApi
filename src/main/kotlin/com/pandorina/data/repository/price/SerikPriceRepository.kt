package com.pandorina.data.repository.price

import com.pandorina.domain.model.JsoupPrice
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.SyncResponse

class SerikPriceRepository: BasePriceRepository() {

    companion object{
        const val cityId = "serik"
        const val srcUrl = "https://fiyat.serikhal.com/"
    }
    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup.select("table.table > tbody > tr")
                    val date = jsoup.select("h3.text-center").text()
                    for (i in 1 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(1)?.text()
                        val measure = row.getOrNull(0)?.text()
                        val price = row.getOrNull(2)?.text()
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
                }
            }
        ).invoke().saveToDatabase()
    }
}