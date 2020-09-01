package com.softwaret.kpdf.util.extension

import io.ktor.application.ApplicationCall
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.principal

val ApplicationCall.userLoginFromPrincipal
    get() = checkNotNull(principal<JWTPrincipal>()?.payload?.userLogin) { "User authentication required" }
