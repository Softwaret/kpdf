package com.softwaret.kpdf.application

import com.fasterxml.jackson.databind.SerializationFeature
import com.softwaret.kpdf.controller.bindControllers
import com.softwaret.kpdf.db.Db
import com.softwaret.kpdf.interactor.bindInteractors
import com.softwaret.kpdf.routing.routes.login
import com.softwaret.kpdf.routing.routes.register
import com.softwaret.kpdf.service.bindServices
import com.softwaret.kpdf.util.extension.instance
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.PartialContent
import io.ktor.jackson.jackson
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.routing.routing
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused")
fun Application.main() {
    installFeatures()
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
}

private fun Application.bindDI() {
    Db.init()

    di {
        bindControllers()
        bindInteractors()
        bindServices()
    }
}

@KtorExperimentalLocationsAPI
private fun Application.bindRouting() {
    routing {
        login(instance())
        register(instance())
    }
}