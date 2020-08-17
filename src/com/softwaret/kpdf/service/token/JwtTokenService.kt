package com.softwaret.kpdf.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Milliseconds
import java.util.*

class JwtTokenService(
    private val algorithm: Algorithm,
    private val expirationPeriod: Milliseconds,
    private val issuer: String
) : TokenService {

    companion object {
        const val LOGIN_CLAIM_NAME = "login"

        fun buildVerifier(algorithm: Algorithm, issuer: String): JWTVerifier =
            JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
    }

    override fun generateToken(login: Login): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim(LOGIN_CLAIM_NAME, login.value)
        .withExpiresAt(obtainExpirationDate())
        .sign(algorithm)

    private fun obtainExpirationDate() = Date(System.currentTimeMillis() + expirationPeriod.value)
}
