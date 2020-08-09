package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password

interface LoginInteractor {

    fun areCredentialsValid(login: Login, password: Password): Boolean

    fun generateToken(login: Login, password: Password): String
}
