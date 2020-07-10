package com.softwaret.kpdf

import com.softwaret.kpdf.controller.bindControllers
import com.softwaret.kpdf.routing.routes.login
import com.softwaret.kpdf.util.instance
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.routing.routing
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@KtorExperimentalLocationsAPI
@Suppress("unused")
fun Application.main() {

    install(Locations)

    di {
        bindControllers()
    }

    routing {

        login(instance())
    }
}
