@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import io.ktor.config.ApplicationConfig

fun ApplicationConfig.stringProperty(path: String) = property(path).getString()

fun ApplicationConfig.longProperty(path: String) = property(path).getString().toLong()
