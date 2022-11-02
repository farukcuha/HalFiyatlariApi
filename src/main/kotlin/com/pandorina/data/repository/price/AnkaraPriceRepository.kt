package com.pandorina.data.repository.price

import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.domain.model.JsoupPrice
import com.pandorina.domain.model.SyncResponse

class AnkaraPriceRepository: BasePriceRepository() {

    companion object{
        const val cityId = "ankara"
        const val srcUrl = "https://www.ankara.bel.tr/hal-fiyatlari"
    }

    override suspend fun syncPrices(): SyncResponse? {
        return HtmlFetcher<List<JsoupPrice>>(
            url = srcUrl,
            parseHtml = { jsoup ->
                mutableListOf<JsoupPrice>().apply {
                    val elements = jsoup
                        .select("table[class=table table-striped table-bordered gy-4 gx-4 mb-0] > tbody > tr")
                    for (i in 0 until elements.size){
                        val row = elements[i].select("td")
                        val name = row.getOrNull(0)?.text()
                        val measure = row.getOrNull(1)?.text()
                        val lowPrice = row.getOrNull(2)?.text()
                        val highPrice = row.getOrNull(3)?.text()
                        val date = row.getOrNull(4)?.text()
                        if (name != null && measure != null && lowPrice != null && highPrice != null && date != null)
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