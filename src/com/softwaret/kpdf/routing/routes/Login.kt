package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.util.extension.respond
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.login(controller: LoginController) {

    @Location("/login")
    class Login

    post<Login> {
        with(call) {
            respond(controller.login(receive()))
        }
    }
}