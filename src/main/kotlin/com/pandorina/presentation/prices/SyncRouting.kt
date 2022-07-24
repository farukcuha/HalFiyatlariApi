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
            CityConfig.Kumluca.id -> {
                KumlucaPriceRepository().syncPrices()
            }
            CityConfig.Demre.id -> {
                DemrePriceRepository().syncPrices()
            }
            CityConfig.Finike.id -> {
                FinikePriceRepository().syncPrices()
            }
            CityConfig.Alanya.id -> {
                AlanyaPriceRepository().syncPrices()
            }
            CityConfig.Serik.id -> {
                SerikPriceRepository().syncPrices()
            }
            CityConfig.Gazipasa.id -> {
                GazipasaPriceRepository().syncPrices()
            }
            CityConfig.Antalya.id -> {
                AntalyaPriceRepository().syncPrices()
            }
            CityConfig.Istanbul.id -> {
                IstanbulPriceRepository().syncPrices()
            }
            CityConfig.Bozyazi.id -> {
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