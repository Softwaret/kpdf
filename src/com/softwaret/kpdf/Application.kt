package com.softwaret.kpdf

import com.softwaret.kpdf.controller.bindControllers
import com.softwaret.kpdf.controller.hello.HelloController
import com.softwaret.kpdf.util.instance
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.kodein.di.ktor.di

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.main() {

    di {
        bindControllers()
    }

    routing {

        val helloController by instance<HelloController>()

        get("/hello") {
            call.respondText(helloController.sayHello())
        }
    }
}
