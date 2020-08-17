package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.util.extension.get
import com.softwaret.kpdf.util.extension.respond
import com.softwaret.kpdf.util.parameters.BodyParameter.LOGIN
import com.softwaret.kpdf.util.parameters.BodyParameter.PASSWORD
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.Parameters
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
            respond(loginUser(controller))
        }
    }
}

private suspend fun ApplicationCall.loginUser(controller: LoginController) =
    receive<Parameters>()[LOGIN, PASSWORD].let { (login, password) ->
        controller.login(login, password)
    }
