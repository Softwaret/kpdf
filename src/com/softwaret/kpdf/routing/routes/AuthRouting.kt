package com.softwaret.kpdf.routing.routes

import com.softwaret.kpdf.controller.login.AuthController
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.util.extension.respondWith
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Routing.auth(controller: AuthController) {

    @Location("/login")
    data class PostLogin(val login: String, val password: String)

    @Location("/refresh")
    data class PostRefresh(val login: String, val refreshToken: String)

    post<PostLogin> { loginModel ->
        call.respondWith(
            controller.login(Login(loginModel.login), Password(loginModel.password))
        )
    }

    post<PostRefresh> { refreshModel ->
        call.respondWith(
            controller.refreshToken(Login(refreshModel.login), refreshModel.refreshToken)
        )
    }
}
