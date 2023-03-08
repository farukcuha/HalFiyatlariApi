package com.pandorina.routes.news

import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.repository.news.NewsRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.newsRouting(){
    get{
        val categoryId = call.request.queryParameters["category_id"] ?: return@get call.respond(
            message = "Missing category id!",
            status = HttpStatusCode.BadRequest
        )
        when(val result = NewsRepository(categoryId).fetchNews()){
            is JsoupResult.Success -> {
                result.data?.let { call.respond(it) }
            }
            is JsoupResult.Error -> {
                result.e?.let { call.respond(it.localizedMessage) }
            }
            else -> return@get call.respond("Something went wrong!")
        }
    }
}