package com.pandorina.presentation.prices

import com.pandorina.data.local.PricesDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteRouting(){
    post("/delete/{cityId}") {
        val cityId = call.parameters["cityId"] ?: return@post call.respond(
            status = HttpStatusCode.BadRequest,
            message = "Missing city id"
        )
        val date = call.request.queryParameters["date"]
        if (date == null){
            PricesDataSource.deletePricesByCityId(cityId)
            return@post call.respond("Deleted all prices that has this id : $cityId")
        }
        PricesDataSource.deletePricesByCityAndDate(cityId, date)
        call.respond("Deleted all prices that has this id : $cityId and this date $date")
    }
    post("/delete_all") {
        PricesDataSource.deleteAllPrices()
        call.respond("Deleted all prices!")
    }
}