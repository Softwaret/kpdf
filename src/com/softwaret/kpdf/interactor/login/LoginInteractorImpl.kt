package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.service.user.UserService

class LoginInteractorImpl(
    private val userService: UserService
) : LoginInteractor {

    override fun areCredentialsValid(login: Login, password: Password) =
        userService.areCredentialsValid(login, password)

    override fun generateToken(login: Login, password: Password): String {
        TODO("not implemented")
    }
}
