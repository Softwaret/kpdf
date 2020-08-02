package com.softwaret.kpdf.util.parameters

import com.auth0.jwt.algorithms.Algorithm
import com.softwaret.kpdf.service.user.UserService

data class TokenServiceParameters(
    val algorithm: Algorithm,
    val expirationTime: Long,
    val realm: String,
    val claimName: String,
    val issuer: String
)