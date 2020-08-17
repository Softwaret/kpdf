package com.softwaret.kpdf.service.token

import com.auth0.jwt.algorithms.Algorithm
import java.util.Date

class JwtTokenService(
    private val algorithm: Algorithm,
    private val expirationTimeMillis: Long
) : TokenService {

    override fun generateToken(claim: String): String = "TOKEN"

    private fun getExpiration() = Date(System.currentTimeMillis() + expirationTimeMillis)
}
