package com.pandorina.presentation.photos

import com.pandorina.data.local.photos
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.photosRouting(){
    get{
        call.respond(photos.shuffled().take(5))
    }
}