package com.pandorina

import io.ktor.server.application.*
import com.pandorina.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

const val BASE_URL = "http://halfiyatlariapi-env-2.eba-dht3rpjn.us-east-1.elasticbeanstalk.com"

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureRouting()
    configureSerialization()
}
