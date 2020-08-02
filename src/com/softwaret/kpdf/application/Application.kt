package com.softwaret.kpdf.application

import com.auth0.jwt.algorithms.Algorithm
import com.fasterxml.jackson.databind.SerializationFeature
import com.softwaret.kpdf.controller.bindControllers
import com.softwaret.kpdf.db.Db
import com.softwaret.kpdf.interactor.bindInteractors
import com.softwaret.kpdf.routing.routes.login
import com.softwaret.kpdf.routing.routes.register
import com.softwaret.kpdf.service.bindServices
import com.softwaret.kpdf.util.extension.instance
import com.softwaret.kpdf.util.parameters.ServiceParameters
import com.softwaret.kpdf.util.parameters.TokenServiceParameters
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.auth.jwt.jwt
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.PartialContent
import io.ktor.jackson.jackson
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.routing.routing
import io.ktor.util.KtorExperimentalAPI
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

@KtorExperimentalAPI
@KtorExperimentalLocationsAPI
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
    install(Authentication) {
        jwt {

        }
    }
    install(DefaultHeaders)
    install(PartialContent)
}

private fun setupDb() {
    Db.init()
}

@KtorExperimentalAPI
private fun Application.bindDI() {
    di {
        bindControllers()
        bindInteractors()
        bindServices(createServiceParameters())
    }
}

@KtorExperimentalLocationsAPI
private fun Application.bindRouting() {

    routing {
        login(instance())
        register(instance())
    }
}

private const val EXPIRATION_TIME: Long = 300_00

@KtorExperimentalAPI
private fun Application.createServiceParameters(): ServiceParameters {

    val secret = environment.config.property("jwt.COS_CO_BEDZIE_POTRZEBNE").getString()

    return ServiceParameters(
        TokenServiceParameters(
            Algorithm.HMAC512(secret),
            EXPIRATION_TIME
        )
    )
}
