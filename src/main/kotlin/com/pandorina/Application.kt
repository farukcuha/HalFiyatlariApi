package com.pandorina

import io.ktor.server.application.*
import com.pandorina.plugins.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

const val BASE_URL = "http://halfiyatlariapi-env-2.eba-dht3rpjn.us-east-1.elasticbeanstalk.com"

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    /*val appConfig = HoconApplicationConfig(ConfigFactory.load())
    Database
        .connect(appConfig.property("jdbc.url").getString(),
            driver = "org.postgresql.Driver",
            user = appConfig.property("jdbc.username").getString(),
            password = appConfig.property("jdbc.password").getString())*/
    AppDatabase.init()
    /*transaction {
        SchemaUtils.create(PriceTable)
    }*/
    configureRouting()
    configureSerialization()
}
