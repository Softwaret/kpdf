package com.softwaret.kpdf.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

open class JWTTokenVeryfingServiceImpl : JWTTokenVeryfingService {

    override fun buildVerifier(algorithm: Algorithm, issuer: String): JWTVerifier =
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
}