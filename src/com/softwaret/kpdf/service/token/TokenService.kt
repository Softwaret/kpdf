package com.softwaret.kpdf.service.token

import com.auth0.jwt.interfaces.JWTVerifier
import io.ktor.auth.Principal

interface TokenService {

    val claimName: String

    val verifier: JWTVerifier

    val realm: String

    fun generateToken(claim: String): String

    fun validate(claimValue: String): Principal?
}