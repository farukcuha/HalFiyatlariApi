package com.pandorina.presentation.prices

import com.pandorina.data.local.CitiesDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getCities(){
    get("/cities"){
        call.respond(CitiesDataSource.cities)
    }
}