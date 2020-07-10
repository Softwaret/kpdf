package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.routing.Login
import com.softwaret.kpdf.util.respond
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.login(loginController: LoginController) {

    post<Login>() {
        with(call) {
            respond(loginController.login(receive()))
        }
    }
}