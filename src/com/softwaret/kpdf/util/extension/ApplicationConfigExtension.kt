@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import com.auth0.jwt.algorithms.Algorithm
import com.softwaret.kpdf.model.inline.Milliseconds
import io.ktor.config.ApplicationConfig

private const val algorithmPath = "jwt.SECRET"
private const val issuerPath = "jwt.ISSUER"
private const val validityPath = "jwt.VALIDITY_MS"
private const val realmPath = "jwt.REALM"

fun ApplicationConfig.stringProperty(path: String) = property(path).getString()

fun ApplicationConfig.longProperty(path: String) = property(path).getString().toLong()

val ApplicationConfig.algorithm: Algorithm
    get() = Algorithm.HMAC512(stringProperty(algorithmPath))

val ApplicationConfig.issuer
    get() = stringProperty(issuerPath)

val ApplicationConfig.validity
    get() = Milliseconds(longProperty(validityPath))

val ApplicationConfig.realm
    get() = stringProperty(realmPath)
