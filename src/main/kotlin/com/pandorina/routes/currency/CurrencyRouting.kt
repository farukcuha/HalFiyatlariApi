package com.pandorina.routes.currency

import com.pandorina.data.remote.JsoupResult
import com.pandorina.data.repository.currency.CurrenciesRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.currenciesRouting(){
    get {
        when(val result = CurrenciesRepository().fetchCurrencies()){
            is JsoupResult.Success -> {
                result.data?.let { call.respond(it) }
            }
            is JsoupResult.Error -> {
                result.e?.let { call.respond(it.localizedMessage) }
            }
            else -> return@get call.respond("Something went wrong!")
        }
    }
}