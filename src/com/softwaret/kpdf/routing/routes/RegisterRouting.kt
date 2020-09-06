package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.register.RegisterController
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.util.extension.respondWith
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.register(controller: RegisterController) {

    @Location("/register")
    data class RegisterLocation(val login: String, val password: String, val name: String)

    post<RegisterLocation> { registerModel ->
        call.respondWith(
            controller.register(Login(registerModel.login), Password(registerModel.password), Name(registerModel.name))
        )
    }
}
