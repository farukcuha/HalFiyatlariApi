package com.pandorina.presentation.prices

import com.pandorina.data.local.price.PricesDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.datesRouting(){
    get("/dates/{cityId}"){
        val cityId = call.parameters["cityId"] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Missing city id"
        )
        call.respond(PricesDataSource.getPriceDates(cityId))
    }
}