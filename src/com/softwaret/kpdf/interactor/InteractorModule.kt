package com.softwaret.kpdf.interactor

import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.interactor.login.LoginInteractorImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindInteractors() {

    bind<LoginInteractor>() with singleton { LoginInteractorImpl() }
}
