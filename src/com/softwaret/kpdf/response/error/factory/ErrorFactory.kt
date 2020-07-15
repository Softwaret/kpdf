package com.softwaret.kpdf.response.error.factory

import com.softwaret.kpdf.response.error.Error
import com.softwaret.kpdf.response.error.ErrorType

interface ErrorFactory {

    fun build(errorType: ErrorType): Error
}