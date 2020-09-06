package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.util.extension.respondWith
import io.ktor.application.call
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.routing.Routing

@KtorExperimentalLocationsAPI
fun Routing.login(controller: LoginController) {

    @Location("/login")
    data class LoginLocation(val login: String, val password: String)

    post<LoginLocation> { loginModel ->
        call.respondWith(
            controller.login(Login(loginModel.login), Password(loginModel.password))
        )
    }
}
