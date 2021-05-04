@file:Suppress("FunctionName")

package com.softwaret.kpdf.response

import io.ktor.http.HttpStatusCode

data class Response(
    val code: HttpStatusCode,
    val body: ResponseBody
) {
    companion object
}

fun Response.Companion.unprocessableEntity(body: ResponseBody) =
    Response(HttpStatusCode.UnprocessableEntity, body)

fun Response.Companion.unauthorized(body: ResponseBody) =
    Response(HttpStatusCode.Unauthorized, body)

fun Response.Companion.ok(body: ResponseBody) =
    Response(HttpStatusCode.OK, body)

fun Response.Companion.created(body: ResponseBody) =
    Response(HttpStatusCode.Created, body)

fun Response.Companion.badRequest(body: ResponseBody) =
    Response(HttpStatusCode.BadRequest, body)

fun Response.Companion.notFound(body: ResponseBody) =
    Response(HttpStatusCode.NotFound, body)

fun Response.Companion.internalServerError(body: ResponseBody) =
    Response(HttpStatusCode.InternalServerError, body)

fun Response.Companion.conflict(body: ResponseBody) =
    Response(HttpStatusCode.Conflict, body)
