package com.softwaret.kpdf.controller

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.controller.login.LoginControllerImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindControllers() {

    bind<LoginController>() with singleton {
        LoginControllerImpl()
    }
}