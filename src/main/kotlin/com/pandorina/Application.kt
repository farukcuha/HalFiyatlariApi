package com.pandorina

import com.pandorina.data.local.photo.PhotosDataSource
import com.pandorina.data.local.photo.PhotosTable
import io.ktor.server.application.*
import com.pandorina.plugins.*
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    AppDatabase.init()
    configureAuthentication()
    configureRouting()
    configureSerialization()
}
