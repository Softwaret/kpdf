package com.softwaret.kpdf.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier
import com.softwaret.kpdf.service.user.UserService
import java.util.*

class JwtTokenService(
    private val algorithm: Algorithm,
    private val expirationTimeMillis: Long,
    override val realm: String,
    override val claimName: String,
    private val userService: UserService,
    private val issuer: String
) : TokenService {

    override val verifier: JWTVerifier by lazy {
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
    }

    override fun generateToken(claim: String): String =
        JWT.create()
            .withIssuer(issuer)
            .withClaim(claimName, claim)
            .withExpiresAt(getExpiration())
            .sign(algorithm)

    override fun validate(claimValue: String) =
        userService.userByLogin(claimValue)?.let {
            UserPrincipal(it.login)
        }

    private fun getExpiration() = Date(System.currentTimeMillis() + expirationTimeMillis)
}