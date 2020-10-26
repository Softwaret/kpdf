package com.softwaret.kpdf.controller

import com.softwaret.kpdf.controller.login.LoginController
import com.softwaret.kpdf.controller.login.LoginControllerImpl
import com.softwaret.kpdf.controller.publication.PublicationsController
import com.softwaret.kpdf.controller.publication.PublicationsControllerImpl
import com.softwaret.kpdf.controller.register.RegisterController
import com.softwaret.kpdf.controller.register.RegisterControllerImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.bindControllers() {

    bind<LoginController>() with singleton { LoginControllerImpl(instance(), instance()) }

    bind<RegisterController>() with singleton { RegisterControllerImpl(instance(), instance()) }

    bind<PublicationsController>() with singleton { PublicationsControllerImpl(instance()) }
}
