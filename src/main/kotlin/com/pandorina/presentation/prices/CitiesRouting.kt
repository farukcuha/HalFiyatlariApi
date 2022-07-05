package com.pandorina.presentation.prices

import com.pandorina.data.local.PricesDataSource.cities
import com.pandorina.data.repository.price.AntalyaPriceRepository
import com.pandorina.data.repository.price.DemrePriceRepository
import com.pandorina.data.repository.price.FinikePriceRepository
import com.pandorina.data.repository.price.KumlucaPriceRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.citiesRouting(){
    get("/cities"){
        call.respond(cities)
    }
}