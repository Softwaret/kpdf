package com.softwaret.kpdf.service.token

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

class JWTTokenVerifierServiceImpl(algorithm: Algorithm, issuer: String) : JWTTokenVerifierService {

    private val verifier = lazy {
        JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
    }

    override fun obtainVerifier(): JWTVerifier = verifier.value
}
