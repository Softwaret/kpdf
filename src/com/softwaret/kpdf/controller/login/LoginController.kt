package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.response.Response

interface LoginController {

    suspend fun login(login: String?, password: String?): Response
}
