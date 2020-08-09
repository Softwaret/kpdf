package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.Response

interface RegisterController {

    fun register(login: Login, password: Password, name: Name): Response
}
