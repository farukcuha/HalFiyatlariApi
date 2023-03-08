package com.pandorina.data.remote

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class HtmlFetcher<T>(
    val url: String,
    val parseHtml: (Document) -> T) {
    suspend operator fun invoke(): Flow<JsoupResult<T>> = flow {
        try {
            val jsoup = Jsoup.connect(url)
                .get()
            emit(JsoupResult.Success(parseHtml.invoke(jsoup)))
        } catch (e: Exception){
            emit(JsoupResult.Error(e))
        }
    }
}