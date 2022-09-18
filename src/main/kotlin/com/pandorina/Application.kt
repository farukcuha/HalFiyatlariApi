package com.pandorina

import com.pandorina.data.local.PriceTable
import io.ktor.server.application.*
import com.pandorina.plugins.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

const val BASE_URL = "http://halfiyatlariapi-env-2.eba-dht3rpjn.us-east-1.elasticbeanstalk.com"

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    Database
        .connect("jdbc:postgresql://my-database.cgvna1jghudf.us-east-1.rds.amazonaws.com/my_database_name",
            driver = "org.postgresql.Driver",
            user = "farukcuha", password = "Ahmet+2002")
    transaction {
        SchemaUtils.create(PriceTable)
    }
    configureRouting()
    configureSerialization()
}
