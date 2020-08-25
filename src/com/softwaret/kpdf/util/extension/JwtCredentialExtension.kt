package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.service.token.JwtTokenService
import io.ktor.auth.jwt.JWTCredential

val JWTCredential.loginFromPayload
    get() = Login(payload.getClaim(JwtTokenService.LOGIN_CLAIM_NAME).asString())
