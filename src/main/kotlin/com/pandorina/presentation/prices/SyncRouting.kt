package com.pandorina.presentation.prices

import com.pandorina.data.repository.price.*
import com.pandorina.domain.config.*
import com.pandorina.domain.model.SyncResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.syncRouting(){
    post("/sync/{cityId}") {
        val cityId = call.parameters["cityId"]
            ?: return@post call.respond(
                status = HttpStatusCode.BadRequest,
                message = "Missing city id!"
            )
        val result: SyncResponse? = when (cityId) {
            KumlucaPriceRepository.cityId -> {
                KumlucaPriceRepository().syncPrices()
            }
            DemrePriceRepository.cityId -> {
                DemrePriceRepository().syncPrices()
            }
            FinikePriceRepository.cityId -> {
                FinikePriceRepository().syncPrices()
            }
            AlanyaPriceRepository.cityId -> {
                AlanyaPriceRepository().syncPrices()
            }
            SerikPriceRepository.cityId -> {
                SerikPriceRepository().syncPrices()
            }
            GazipasaPriceRepository.cityId -> {
                GazipasaPriceRepository().syncPrices()
            }
            AntalyaPriceRepository.cityId -> {
                AntalyaPriceRepository().syncPrices()
            }
            IstanbulPriceRepository.cityId -> {
                IstanbulPriceRepository().syncPrices()
            }
            BozyaziPriceRepository.cityId -> {
                BozyaziPriceRepository().syncPrices()
            }
            else -> return@post call.respond(
                status = HttpStatusCode.BadRequest,
                message ="Unknown city id!")
        }
        call.respondText(
            status = HttpStatusCode.fromValue(result?.statusCode ?: 404),
            text = result?.message ?: "Unknown error!")
    }
}