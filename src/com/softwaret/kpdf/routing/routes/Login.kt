package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.routing.Login
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.get
import io.ktor.response.respondText
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.login(loginController: LoginController) {

    get<Login>() {
        call.respondText {
            loginController.sayLogin()
        }
    }
}