@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import com.auth0.jwt.algorithms.Algorithm
import com.softwaret.kpdf.model.inline.Milliseconds
import io.ktor.config.ApplicationConfig

val ApplicationConfig.algorithm: Algorithm
    get() = Algorithm.HMAC512(stringProperty("jwt.SECRET"))

val ApplicationConfig.issuer
    get() = stringProperty("jwt.ISSUER")

val ApplicationConfig.validity
    get() = Milliseconds(longProperty("jwt.VALIDITY_MS"))

val ApplicationConfig.realm
    get() = stringProperty("jwt.REALM")
