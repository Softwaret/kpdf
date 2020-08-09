package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.Response

interface LoginController {

    suspend fun login(login: Login, password: Password): Response
}
