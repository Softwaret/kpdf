package com.softwaret.kpdf.service.token

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.JWTVerifier

interface JWTTokenVeryfingService {

    fun buildVerifier(algorithm: Algorithm, issuer: String): JWTVerifier
}