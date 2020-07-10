package com.softwaret

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.kodein.di.Kodein

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {

    routing {
        get("/check") {
            call.respondText("KPDF")
        }
    }
}
