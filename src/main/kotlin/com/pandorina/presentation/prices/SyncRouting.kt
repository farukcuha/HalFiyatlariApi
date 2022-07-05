package com.pandorina.presentation.prices

import com.pandorina.data.repository.price.*
import com.pandorina.domain.config.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

const val MESSAGE_FETCHING_IS_FAILED = "Birşeyler ters gitti!"
const val MESSAGE_MISSING_CITY_ID = "Şehir kimliği bulunamadı!"

fun Route.syncRouting(){
    post("/sync") {
        val cityId = call.request.queryParameters["cityId"]?.toInt()
            ?: return@post call.respond(
                status = HttpStatusCode.BadRequest,
                message = MESSAGE_MISSING_CITY_ID
            )
        when (cityId) {
            kumlucaConfig.id -> {
                val result = KumlucaPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            demreConfig.id -> {
                val result = DemrePriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            finikeConfig.id -> {
                val result = FinikePriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            alanyaConfig.id -> {
                val result = AlanyaPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            serikConfig.id -> {
                val result = SerikPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            gazipasaConfig.id -> {
                val result = GazipasaPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            antalyaConfig.id -> {
                val result = AntalyaPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            istanbulConfig.id -> {
                val result = IstanbulPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
            bozyaziConfig.id -> {
                val result = BozyaziPriceRepository().syncPrices()
                    ?: return@post call.respond(MESSAGE_FETCHING_IS_FAILED)
                call.respondText(result)
            }
        }
    }
}