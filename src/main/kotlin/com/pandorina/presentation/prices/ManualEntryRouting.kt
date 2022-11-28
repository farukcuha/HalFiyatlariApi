package com.pandorina.presentation.prices

import com.pandorina.data.local.price.PriceTable
import com.pandorina.data.local.price.PricesDataSource
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.text.SimpleDateFormat
import java.util.*

val bozyaziFields = arrayOf(
    Pair("Patlıcan", "https://cdn-icons-png.flaticon.com/512/766/766026.png"),
    Pair("Salatalık", "https://cdn-icons-png.flaticon.com/512/2346/2346905.png"),
    Pair("Fasulye", "https://cdn1.iconfinder.com/data/icons/food-2-23/50/125-512.png"),
    Pair("Kıl Biber", "https://cdn-icons-png.flaticon.com/512/2909/2909883.png"),
    Pair("Sivri Biber", "https://cdn-icons-png.flaticon.com/512/2909/2909883.png")
)

@kotlinx.serialization.Serializable
data class IncomingPrices(
    val time: Long = System.currentTimeMillis(),
    val values: List<Float>
)

fun Route.manualEntryRouting(){
    route("/manual_entry"){
        get("/bozyazi") {
            call.respond(bozyaziFields.map {
                it.first
            })
        }
        post("/bozyazi") {
            val incomingPrices = call.receive<IncomingPrices>()
            val date = SimpleDateFormat("dd MMMM yyyy EEEE", Locale("tr"))
                .format(incomingPrices.time)
            var message = ""
            transaction {
                try {
                    PriceTable.deleteWhere {
                        PriceTable.priceDate eq date
                    }
                    bozyaziFields.forEachIndexed { i, field ->
                        PriceTable.insert {
                            it[id] = "$date - ${field.first} - ${incomingPrices.values[i]}"
                            it[cityId] = "bozyazi"
                            it[priceDate] = date
                            it[lastUpdatedTime] = System.currentTimeMillis()
                            it[name] = field.first
                            it[icon] = field.second
                            it[measure] = "Kilogram"
                            it[pricePrimary] = "${incomingPrices.values[i]}₺"
                            it[priceSecondary] = null
                        }
                    }
                    message = "Değerler başarılı bir şekilde kaydedildi!"
                } catch (e: Exception){
                    message = "HATA - ${e.localizedMessage}"
                }
            }
            call.respond(message)
        }
    }
}