package com.softwaret.kpdf.interactor

import com.softwaret.kpdf.interactor.login.AuthInteractor
import com.softwaret.kpdf.interactor.login.AuthInteractorImpl
import com.softwaret.kpdf.interactor.publication.PublicationsInteractor
import com.softwaret.kpdf.interactor.publication.PublicationsInteractorImpl
import com.softwaret.kpdf.interactor.register.RegisterInteractor
import com.softwaret.kpdf.interactor.register.RegisterInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton

fun DI.MainBuilder.bindInteractors() {

    bind<AuthInteractor>() with singleton { AuthInteractorImpl(instance(), instance()) }

    bind<RegisterInteractor>() with singleton { RegisterInteractorImpl(instance()) }

    bind<PublicationsInteractor>() with singleton { PublicationsInteractorImpl(instance()) }
}
