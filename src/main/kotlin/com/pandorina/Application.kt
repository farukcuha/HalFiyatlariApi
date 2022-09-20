package com.pandorina

import io.ktor.server.application.*
import com.pandorina.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

val BASE_URL: String = System.getenv("BASE_URL")

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    AppDatabase.init()
    configureRouting()
    configureSerialization()
}
