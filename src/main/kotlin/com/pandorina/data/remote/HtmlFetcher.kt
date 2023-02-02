package com.pandorina.data.remote

import io.ktor.server.util.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.URL


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