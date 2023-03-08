package com.pandorina.data.repository.news

import com.pandorina.data.local.news.NewsDataSource
import com.pandorina.model.News
import com.pandorina.data.remote.HtmlFetcher
import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.remote.collectJsoupResult
import com.pandorina.data.local.news.URL_SON_DAKIKA

class NewsRepository(
    val id: String
) {
    suspend fun fetchNews(): JsoupResult<List<News>?>? {
        val url = NewsDataSource.categories.find {
            it.id.toString() == id
        }?.srcUrl ?: return null

        var result: JsoupResult<List<News>?>? = null

        HtmlFetcher(
            url = url,
            parseHtml = { document ->
                mutableListOf<News>().apply {
                    document.select("li[class=nws]").take(60).forEach { element ->
                        add(
                            News(
                                time = element.select("span[class=hour data_calc]").text(),
                                path = "$URL_SON_DAKIKA${element.select("a[class=fr]").attr("href")}",
                                image = element.select("a[class=fr] > img").attr("data-original"),
                                title = element.select("a[class=content]").text(),
                                content = element.select("p[class=news-detail news-column]").text()
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