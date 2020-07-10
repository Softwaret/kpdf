package com.softwaret.kpdf.controller

import com.softwaret.kpdf.controller.hello.HelloController
import com.softwaret.kpdf.controller.hello.HelloControllerImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindControllers() {

    bind<HelloController>() with singleton {
        HelloControllerImpl()
    }
}