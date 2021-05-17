package com.softwaret.kpdf.service.token

import com.auth0.jwt.interfaces.JWTVerifier

interface JWTTokenVerifierService {

    fun obtainVerifier(): JWTVerifier
}
