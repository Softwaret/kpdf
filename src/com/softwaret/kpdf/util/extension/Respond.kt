package com.softwaret.kpdf.util.extension

import com.softwaret.kpdf.response.Response
import io.ktor.application.ApplicationCall
import io.ktor.response.respond

/**
 * Sets status from [response] and sends a message as a response
 */
suspend inline fun ApplicationCall.respond(response: Response) =
    respond(response.code, response.body)
