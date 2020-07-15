package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.register.RegisterController
import com.softwaret.kpdf.routing.Register
import com.softwaret.kpdf.util.respond
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.register(registerController: RegisterController) {

    post<Register>() {
        with(call) {
            respond(registerController.register(receive()))
        }
    }
}