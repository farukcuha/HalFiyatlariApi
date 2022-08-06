package com.pandorina.presentation.prices

import com.pandorina.data.repository.price.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.syncAllRouting(){
    post("/sync_all") {
        KumlucaPriceRepository().syncPrices()?: return@post call.respond("Kumluca sync is failed!")
        DemrePriceRepository().syncPrices()?: return@post call.respond("Demre sync is failed!")
        FinikePriceRepository().syncPrices()?:  return@post call.respond("Finike sync is failed!")
        AlanyaPriceRepository().syncPrices()?: return@post call.respond("Alanya sync is failed!")
        SerikPriceRepository().syncPrices()?: return@post call.respond("Serik sync is failed!")
        GazipasaPriceRepository().syncPrices()?: return@post call.respond("Gazipasa sync is failed!")
        AntalyaPriceRepository().syncPrices()?:  return@post call.respond("Antalya sync is failed!")
        IstanbulPriceRepository().syncPrices()?: return@post call.respond("İstanbul sync is failed!")
        //BozyaziPriceRepository().syncPrices()?: return@post call.respond("Bozyazi sync is failed!")
        call.respond("Sync is successfull!")
    }
}