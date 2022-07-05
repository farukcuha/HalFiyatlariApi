package com.pandorina

import com.pandorina.data.local.PriceTable
import io.ktor.server.application.*
import com.pandorina.plugins.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    Database.connect("jdbc:h2:./hal_fiyatlari_database", "org.h2.Driver")
    transaction {
        SchemaUtils.create(PriceTable)
    }
    configureRouting()
    configureSerialization()
}
