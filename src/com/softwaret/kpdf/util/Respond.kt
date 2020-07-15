package com.softwaret.kpdf.util

import com.softwaret.kpdf.response.Response
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

suspend inline fun ApplicationCall.respond(response: Response) =
    respond(response.code, response.body)