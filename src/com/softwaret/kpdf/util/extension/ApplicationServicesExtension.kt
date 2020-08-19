package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.service.token.JWTTokenVeryfingService
import com.softwaret.kpdf.service.user.UserService
import io.ktor.application.Application

val Application.userService
    get() = instance<UserService>()

val Application.jwtTokenVerifierService
    get() = instance<JWTTokenVeryfingService>()

