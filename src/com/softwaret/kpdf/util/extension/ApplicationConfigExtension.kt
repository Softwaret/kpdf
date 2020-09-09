@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import com.auth0.jwt.algorithms.Algorithm
import com.softwaret.kpdf.model.inline.Milliseconds
import io.ktor.config.ApplicationConfig

val ApplicationConfig.algorithmPath
    get() = "jwt.SECRET"

val ApplicationConfig.issuerPath
    get() = "jwt.ISSUER"

val ApplicationConfig.validityPath
    get() = "jwt.VALIDITY_MS"

val ApplicationConfig.realmPath
    get() = "jwt.REALM"

val ApplicationConfig.algorithm: Algorithm
    get() = Algorithm.HMAC512(stringProperty(algorithmPath))

val ApplicationConfig.issuer
    get() = stringProperty(issuerPath)

val ApplicationConfig.validity
    get() = Milliseconds(longProperty(validityPath))

val ApplicationConfig.realm
    get() = stringProperty(realmPath)
