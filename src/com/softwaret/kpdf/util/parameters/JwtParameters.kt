package com.softwaret.kpdf.util.parameters

import com.auth0.jwt.algorithms.Algorithm
import com.softwaret.kpdf.model.inline.Milliseconds

data class JwtParameters(
    val algorithm: Algorithm,
    val validityPeriod: Milliseconds,
    val issuer: String
)

dupa
