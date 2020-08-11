@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.application

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.SerializationFeature
import com.softwaret.kpdf.controller.bindControllers
import com.softwaret.kpdf.db.H2Db
import com.softwaret.kpdf.db.tables.user.User
import com.softwaret.kpdf.db.tables.user.Users
import com.softwaret.kpdf.interactor.bindInteractors
import com.softwaret.kpdf.model.inline.Milliseconds
import com.softwaret.kpdf.repository.bindPreferences
import com.softwaret.kpdf.routing.routes.login
import com.softwaret.kpdf.routing.routes.register
import com.softwaret.kpdf.service.bindServices
import com.softwaret.kpdf.service.token.JwtTokenService.Companion.LOGIN_CLAIM_NAME
import com.softwaret.kpdf.util.extension.instance
import com.softwaret.kpdf.util.extension.longProperty
import com.softwaret.kpdf.util.extension.stringProperty
import com.softwaret.kpdf.util.parameters.JwtParameters
import com.softwaret.kpdf.util.parameters.bindParameters
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.JWTCredential
import io.ktor.auth.jwt.JWTPrincipal
import io.ktor.auth.jwt.jwt
import io.ktor.config.ApplicationConfig
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.PartialContent
import io.ktor.jackson.jackson
import io.ktor.locations.Locations
import io.ktor.routing.routing
import org.jetbrains.exposed.sql.transactions.transaction
import org.kodein.di.ktor.di
import java.io.File

private const val EXAMPLE_APP_CONF_PATH = "resources/application-example.conf"
private const val APP_CONF_PATH = "resources/application.conf"
private const val CONFIG_ARG_NAME = "-config="

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(addConfFileLocation(args))

fun addConfFileLocation(args: Array<String>) = when {
    args.any { it.contains(CONFIG_ARG_NAME) } -> args
    File(APP_CONF_PATH).exists() -> args + "$CONFIG_ARG_NAME$APP_CONF_PATH"
    File(EXAMPLE_APP_CONF_PATH).exists() -> args + "$CONFIG_ARG_NAME$EXAMPLE_APP_CONF_PATH"
    else -> args
}

@Suppress("unused")
fun Application.main() {
    installFeatures()
    setupDb()
    bindDI()
    bindRouting()
}

private fun Application.installFeatures() {
    install(Locations)
    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }
    install(DefaultHeaders)
    install(PartialContent)
    install(CallLogging)

    install(Authentication) {
        jwt {
            realm = this@installFeatures.environment.config.realm
            verifier(buildJwtVerifier())
            validate { jwtCredential -> validateCredential(jwtCredential) }
        }
    }
}

private fun validateCredential(jwtCredential: JWTCredential) =
    if (userByLoginExists(jwtCredential)) {
        JWTPrincipal(jwtCredential.payload)
    } else {
        null
    }

private fun userByLoginExists(it: JWTCredential) =
    transaction { User.find { Users.login eq it.payload.getClaim(LOGIN_CLAIM_NAME).asString() }.empty().not() }

private fun setupDb() {
    H2Db.init()
}

private fun Application.bindDI() {
    di {
        bindParameters(
            salt = environment.config.stringProperty("config.SALT")
        )
        bindControllers()
        bindInteractors()
        bindServices(obtainJwtParameters())
        bindPreferences()
    }
}

private fun Application.bindRouting() {
    routing {
        login(instance())
        register(instance())
    }
}

private fun Application.obtainJwtParameters() = environment.config.run {
    JwtParameters(
        algorithm,
        validity,
        issuer
    )
}

private fun Application.buildJwtVerifier() = environment.config.run {
    JWT.require(algorithm)
        .withIssuer(issuer)
        .build()
}

private val ApplicationConfig.algorithm
    get() = Algorithm.HMAC512(stringProperty("jwt.SECRET"))
private val ApplicationConfig.issuer
    get() = stringProperty("jwt.ISSUER")
private val ApplicationConfig.validity
    get() = Milliseconds(longProperty("jwt.VALIDITY_MS"))
private val ApplicationConfig.realm
    get() = stringProperty("jwt.REALM")