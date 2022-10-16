package com.pandorina.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*


fun Application.configureAuthentication(){
    install(Authentication) {
        basic("auth-basic") {
            validate { credentials ->
                if (credentials.name == System.getenv("API_USERNAME")
                    && credentials.password == System.getenv("API_PASSWORD")) {
                    UserIdPrincipal(credentials.name)
                } else {
                    null
                }
            }
        }
    }
}