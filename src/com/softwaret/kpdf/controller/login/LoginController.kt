package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.response.Response
import io.ktor.http.Parameters

interface LoginController {

    suspend fun login(parameters: Parameters): Response
}