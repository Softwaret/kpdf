package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.response.Response
import io.ktor.http.Parameters

interface RegisterController {

    fun register(parameters: Parameters): Response
}