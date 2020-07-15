package com.softwaret.kpdf.response.error.factory

import com.softwaret.kpdf.response.error.Error
import com.softwaret.kpdf.response.error.ErrorType

class ErrorFactoryImpl : ErrorFactory {

    override fun build(errorType: ErrorType) =
        Error(errorType.name, errorType.message)
}