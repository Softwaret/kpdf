package com.softwaret.kpdf.response.error.factory

import ErrorFactory
import com.softwaret.kpdf.response.error.Error
import com.softwaret.kpdf.response.error.ErrorType

class ErrorFactoryImpl : ErrorFactory {

   override fun build(errorType: ErrorType) =
        when (errorType) {
            ErrorType.NULL_EMPTY_LOGIN -> Error(errorType.name, errorType.message)
            else -> Error(errorType.name, errorType.message)
        }
}