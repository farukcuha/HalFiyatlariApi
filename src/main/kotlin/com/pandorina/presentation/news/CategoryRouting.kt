package com.pandorina.presentation.news

import com.pandorina.data.local.NewsDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.categoryRouting(){
    get("/categories"){
        call.respond(NewsDataSource.categories)
    }
}