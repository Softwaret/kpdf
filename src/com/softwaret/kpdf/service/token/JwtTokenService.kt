package com.softwaret.kpdf.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTCreator
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTVerificationException
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Milliseconds
import java.util.*

class JwtTokenService(
    private val algorithm: Algorithm,
    private val expirationPeriod: Milliseconds,
    private val issuer: String
) : JWTTokenVerifierServiceImpl(algorithm, issuer), TokenService {

    companion object {

        const val LOGIN_CLAIM_NAME = "login"
    }

    override fun generateToken(login: Login) = buildToken {
        withClaim(LOGIN_CLAIM_NAME, login.value)
            .withExpiresAt(obtainExpirationDate())
    }

    override fun generateRefreshToken(login: Login) = buildToken {
        withClaim(LOGIN_CLAIM_NAME, login.value)
    }

    private fun buildToken(block: JWTCreator.Builder.() -> JWTCreator.Builder): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withIssuedAt(Date())
        .block()
        .sign(algorithm)

    private fun obtainExpirationDate() = Date(System.currentTimeMillis() + expirationPeriod.value)

    override fun isRefreshTokenValid(refreshToken: String): Boolean =
        try {
            obtainVerifier().verify(refreshToken)
            true
        } catch (e: JWTVerificationException) {
            false
        }
}
