package com.softwaret.kpdf.util.parameters

import com.auth0.jwt.algorithms.Algorithm

data class TokenServiceParameters(
    val algorithm: Algorithm,
    val expirationTime: Long
)
