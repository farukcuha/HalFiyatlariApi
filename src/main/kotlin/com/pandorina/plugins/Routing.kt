package com.pandorina.plugins

import com.pandorina.routes.currency.currenciesRouting
import com.pandorina.routes.news.categoryRouting
import com.pandorina.routes.news.newsRouting
import com.pandorina.routes.photo.getPhoto
import com.pandorina.routes.cities.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get {
            return@get call.respond("Welcome to HalFiyatlari server!")
        }
        route("/cities") {
            getCities()
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
