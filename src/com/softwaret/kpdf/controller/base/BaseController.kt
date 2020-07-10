package com.softwaret.kpdf.controller.base

import ErrorFactory
import com.softwaret.kpdf.response.error.ErrorType

abstract class BaseController(
    private val errorFactory: ErrorFactory
) {

    protected fun error(errorType: ErrorType) =
        errorFactory.build(errorType)
}