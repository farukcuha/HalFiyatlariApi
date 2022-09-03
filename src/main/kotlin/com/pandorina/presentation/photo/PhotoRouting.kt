package com.pandorina.presentation.photo

import com.pandorina.data.local.PhotosDataSource
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.getPhoto(){
    get{
        call.respond(PhotosDataSource.photos.random())
    }
}