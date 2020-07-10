package com.softwaret.kpdf.response

import ErrorFactory
import com.softwaret.kpdf.response.error.factory.ErrorFactoryImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindResponses() {

    bind<ErrorFactory>() with singleton { ErrorFactoryImpl() }
}