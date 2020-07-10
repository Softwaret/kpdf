package com.softwaret.kpdf.util

import io.ktor.routing.Routing
import org.kodein.di.direct
import org.kodein.di.ktor.di
import org.kodein.type.generic

inline fun <reified T : Any> Routing.instance(tag: Any? = null) = di().direct.Instance<T>(generic(), tag)

