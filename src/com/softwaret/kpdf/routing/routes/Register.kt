package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.register.RegisterController
import com.softwaret.kpdf.util.extension.respond
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.register(controller: RegisterController) {

    @Location("/register")
    class Register

    post<Register> {
        with(call) {
            respond(controller.register(receive()))
        }
    }
}