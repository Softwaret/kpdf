package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.register.RegisterController
import com.softwaret.kpdf.util.extension.respond
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.register(controller: RegisterController) {

    @Location("/register")
    data class Register(val login: String, val password: String, val name: String)

    post<Register> { registerModel ->
        call.respond(
            controller.register(registerModel.login, registerModel.password, registerModel.name)
        )
    }
}