package com.pandorina.plugins

import com.pandorina.data.local.PricesDataSource
import com.pandorina.presentation.currency.currenciesRouting
import com.pandorina.presentation.news.categoryRouting
import com.pandorina.presentation.news.newsRouting
import com.pandorina.presentation.photo.getPhoto
import com.pandorina.presentation.prices.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get {
            return@get call.respond("Welcome to HalFiyatlari server!")
        }
        route("/prices") {
            syncRouting()
            syncAllRouting()
            getCities()
            datesRouting()
            fetchRouting()
            deleteRouting()
        }
        route("/news"){
            categoryRouting()
            newsRouting()
        }
        route("/photo"){
            getPhoto()
        }
        route("/currency"){
            currenciesRouting()
        }
        static{
            resources("static")
        }
    }
}
