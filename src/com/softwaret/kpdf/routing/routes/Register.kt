package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.register.RegisterController
import com.softwaret.kpdf.util.extension.get
import com.softwaret.kpdf.util.extension.respond
import com.softwaret.kpdf.util.parameters.BodyParameter.*
import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.http.Parameters
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
            respond(registerUser(controller))
        }
    }
}

private suspend fun ApplicationCall.registerUser(controller: RegisterController) =
    receive<Parameters>()[LOGIN, PASSWORD, NAME].let { (login, password, name) ->
        controller.register(login, password, name)
    }
