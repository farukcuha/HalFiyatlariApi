package com.pandorina.presentation.prices

import com.pandorina.data.local.PricesDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.datesRouting(){
    get("/dates"){
        val cityId = call.request.queryParameters["cityId"]?.toInt() ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Missing city id"
        )
        call.respond(PricesDataSource.getPriceDates(cityId))
    }
}