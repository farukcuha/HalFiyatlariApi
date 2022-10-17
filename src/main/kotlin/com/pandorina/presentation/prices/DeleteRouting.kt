package com.pandorina.presentation.prices

import com.pandorina.data.local.price.PricesDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteRouting(){
    post("/delete_all") {
        PricesDataSource.deleteAllPrices()
        call.respond("Deleted all prices!")
    }

    post("/delete_by_days") {
        val dayCount = call.parameters["day"]?.toLong() ?: 15
        PricesDataSource.deletePricesByDays(dayCount)
        call.respond("the prices that is saved $dayCount days ago were deleted.")
    }

    post("/delete_by_city") {
        val cityId = call.parameters["city_id"] ?: return@post call.respond(
            status = HttpStatusCode.BadRequest,
            "Missing city_id!"
        )
        PricesDataSource.deletePricesByCity(cityId)
        call.respond("the prices that are with id : $cityId were deleted.")
    }

}