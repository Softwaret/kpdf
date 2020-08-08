package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.response.Response

interface RegisterController {

    fun register(login: String, password: String, name: String): Response
}
