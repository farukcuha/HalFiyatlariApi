package com.pandorina.presentation.prices

import com.pandorina.data.local.PricesDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.fetchRouting(){
    get("/fetch/{cityId}") {
        val cityId = call.parameters["cityId"] ?: return@get call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Missing city id"
        )
        /*val date = call.request.queryParameters["date"] ?: return@get call.respond(
            PricesDataSource.getLastPrices(cityId)
        )*/
        call.respond(PricesDataSource.getPricesByDate(cityId, null))
    }
}