package com.softwaret.kpdf.util.extension

import io.ktor.application.Application
import io.ktor.routing.Routing
import org.kodein.di.direct
import org.kodein.di.ktor.di
import org.kodein.type.generic

inline fun <reified T : Any> Routing.instance(tag: Any? = null) = di().direct.Instance<T>(generic(), tag)

inline fun <reified T : Any> Application.instance(tag: Any? = null) = di().direct.Instance<T>(generic(), tag)
