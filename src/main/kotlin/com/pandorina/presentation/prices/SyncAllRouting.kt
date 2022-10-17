package com.pandorina.presentation.prices

import com.pandorina.data.repository.price.*
import com.pandorina.domain.model.SyncResponse
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun Route.syncAllRouting(){
    post("/sync_all") {
        KumlucaPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Kumluca sync is failed!")
        }
        DemrePriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Demre sync is failed!")
        }
        FinikePriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Finike sync is failed!")
        }
        AlanyaPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Alanya sync is failed!")
        }
        SerikPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Serik sync is failed!")
        }
        GazipasaPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Gazipaşa sync is failed!")
        }
        AntalyaPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Antalya sync is failed!")
        }
        IstanbulPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("İstanbul sync is failed!")
        }
        AnkaraPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Ankara sync is failed!")
        }
        TrabzonPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Trabzon sync is failed!")
        }
        AdanaHalFiyatları().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Adana sync is failed!")
        }
        DenizliPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Denizli sync is failed!")
        }
        KonyaPriceRepository().syncPrices()?.let {
            if (it.statusCode != 200) return@post call.respond("Konya sync is failed!")
        }
        //BozyaziPriceRepository().syncPrices()?: return@post call.respond("Bozyazi sync is failed!")
        call.respond("Sync is successful!")
    }
}
