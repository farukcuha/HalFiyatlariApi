package com.pandorina.routes.cities

import com.pandorina.data.local.city.CitiesDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getCities(){
    get{
        call.respond(CitiesDataSource.cities)
    }
}