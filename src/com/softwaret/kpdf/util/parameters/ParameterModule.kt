package com.softwaret.kpdf.util.parameters

import com.softwaret.kpdf.util.parameters.ParametersNames.SALT
import org.kodein.di.DI
import org.kodein.di.with

object ParametersNames {
    const val SALT = "SALT"
}

fun DI.MainBuilder.bindParameters(
    salt: String
) {
    constant(tag = SALT) with salt
}
