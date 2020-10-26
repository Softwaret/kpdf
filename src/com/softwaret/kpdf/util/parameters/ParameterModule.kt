package com.softwaret.kpdf.util.parameters

import org.kodein.di.DI
import org.kodein.di.with

const val SALT = "SALT"

fun DI.MainBuilder.bindParameters(salt: String) {
    constant(tag = SALT) with salt
}
