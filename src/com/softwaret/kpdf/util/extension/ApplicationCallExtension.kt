package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.response.Response
import io.ktor.application.ApplicationCall
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.principal
import io.ktor.response.respond

val ApplicationCall.userLoginFromPrincipal
    get() = run {
        val login = principal<JWTPrincipal>()?.payload?.userLogin
        checkNotNull(login) { "User authentication required" }
    }

suspend fun ApplicationCall.respondWith(response: Response) =
    respond(status = response.code, message = response.body)
