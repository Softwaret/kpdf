package com.softwaret.kpdf.response

import io.ktor.http.HttpStatusCode
import java.io.Serializable

data class Response(
    val code: HttpStatusCode,
    val body: Serializable
)