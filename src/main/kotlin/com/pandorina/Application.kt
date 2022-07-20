package com.pandorina

import com.pandorina.data.local.PriceTable
import com.pandorina.data.local.PricesDataSource
import com.pandorina.domain.model.core.Price
import com.pandorina.domain.model.dto.PriceDto
import io.ktor.server.application.*
import com.pandorina.plugins.*
import kotlinx.coroutines.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
}
