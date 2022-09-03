package com.pandorina.data.repository.currency

import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.remote.collectJsoupResult
import com.pandorina.domain.model.Currency

class CurrenciesRepository {

    suspend fun fetchCurrencies(): JsoupResult<List<Currency>?>? {
        val url = "https://www.doviz.com"

        var result: JsoupResult<List<Currency>?>? = null

        HtmlFetcher(
            url = url,
            parseHtml = { document ->
                mutableListOf<Currency>().apply {
                    document.select("div.market-data > div.item").forEach { element ->
                        val name = element.select("span.name").text()
                        val price = element.select("span.value").text()
                        val changeRate = element.select("div.change-rate").text()
                        val changeAmount = element.select("div.change-amount").text()
                        val trendUp = !changeRate.contains("-")
                        add(
                            Currency(
                                name = name,
                                price = price,
                                changeRate = changeRate,
                                changeAmount = changeAmount,
                                trendUp = trendUp
                            )
                        )
                    }
                }
            }
        ).invoke().collectJsoupResult(
            onSuccess = {
                result = JsoupResult.Success(it)
            },
            onFailure = {
                result = JsoupResult.Error(it)
            }
        )
        return result
    }
}