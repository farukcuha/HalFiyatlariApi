package com.pandorina.routes.photo

import com.pandorina.data.local.photo.PhotosDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getPhoto(){
    get {
        call.respond(PhotosDataSource.getPhoto())
    }
}