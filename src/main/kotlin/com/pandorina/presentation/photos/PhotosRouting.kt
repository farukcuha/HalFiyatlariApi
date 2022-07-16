package com.pandorina.presentation.photos

import com.pandorina.data.local.photos
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.photoRouting(){
    get{
        call.respond(photos.random())
    }
}