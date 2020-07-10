package com.softwaret.kpdf.controller.base

import ErrorFactory
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.error.ErrorType
import io.ktor.http.HttpStatusCode
import java.io.Serializable

abstract class BaseController(
    private val errorFactory: ErrorFactory
) {

    protected fun error(errorType: ErrorType) =
        errorFactory.build(errorType)

    protected fun respond(pair: Pair<HttpStatusCode, Serializable>) =
        Response(pair.first, pair.second)
}