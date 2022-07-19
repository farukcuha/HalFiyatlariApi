package com.pandorina.plugins

import com.pandorina.domain.model.core.Price
import com.pandorina.presentation.news.categoryRouting
import com.pandorina.presentation.news.newsRouting
import com.pandorina.presentation.photos.photoRouting
import com.pandorina.presentation.prices.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun Application.configureRouting() {
    routing {
        route("/prices") {
            citiesRouting()
            datesRouting()
            syncRouting()
            fetchRouting()
            deleteRouting()
        }
        route("/news"){
            categoryRouting()
            newsRouting()
        }
        route("/photo"){
            photoRouting()
        }
    }
}
