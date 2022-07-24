package com.pandorina.plugins

import com.pandorina.presentation.news.categoryRouting
import com.pandorina.presentation.news.newsRouting
import com.pandorina.presentation.prices.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/prices") {
            syncRouting()
            syncAllRouting()
        }
        route("/news"){
            categoryRouting()
            newsRouting()
        }
    }
}
