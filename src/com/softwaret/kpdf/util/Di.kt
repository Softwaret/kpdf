package com.softwaret.kpdf.util

import io.ktor.application.Application
import org.kodein.di.DIProperty
import org.kodein.di.instance
import org.kodein.di.ktor.di

inline fun <reified T : Any> Application.instance(tag: Any? = null): DIProperty<T> = di().instance(tag)