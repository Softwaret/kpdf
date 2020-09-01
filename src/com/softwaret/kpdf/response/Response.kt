@file:Suppress("FunctionName")

package com.softwaret.kpdf.response

import io.ktor.http.HttpStatusCode

data class Response(
    val code: HttpStatusCode,
    val body: ResponseBody
) {
    companion object
}

fun Response.Companion.UnprocessableEntity(body: ResponseBody) =
    Response(HttpStatusCode.UnprocessableEntity, body)

fun Response.Companion.Unauthorized(body: ResponseBody) =
    Response(HttpStatusCode.Unauthorized, body)

fun Response.Companion.OK(body: ResponseBody) =
    Response(HttpStatusCode.OK, body)

fun Response.Companion.BadRequest(body: ResponseBody) =
    Response(HttpStatusCode.BadRequest, body)

fun Response.Companion.NotFound(body: ResponseBody) =
    Response(HttpStatusCode.NotFound, body)
