package com.softwaret.kpdf.response

import io.ktor.http.HttpStatusCode

data class Response(
    val code: HttpStatusCode,
    val body: ResponseBody
) {
    companion object
}

inline fun Response.Companion.UnprocessableEntity(body: () -> ResponseBody) = UnprocessableEntity(body())

fun Response.Companion.UnprocessableEntity(body: ResponseBody) =
    Response(HttpStatusCode.UnprocessableEntity, body)

inline fun Response.Companion.Unauthorized(body: () -> ResponseBody) = Unauthorized(body())

fun Response.Companion.Unauthorized(body: ResponseBody) =
    Response(HttpStatusCode.Unauthorized, body)

inline fun Response.Companion.OK(body: () -> ResponseBody) = OK(body())

fun Response.Companion.OK(body: ResponseBody) =
    Response(HttpStatusCode.OK, body)