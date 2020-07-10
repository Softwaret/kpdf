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

    protected suspend fun (suspend (Response) -> (Unit)).trigger(pair: Pair<HttpStatusCode, Serializable>) =
        invoke(Response(pair.first, pair.second))
}