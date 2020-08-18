package com.softwaret.kpdf.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

open class JWTTokenVeryfingServiceImpl : JWTTokenVeryfingService {

    override fun buildVerifier(algorithm: Algorithm, issuer: String): com.auth0.jwt.JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
}