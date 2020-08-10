package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.service.token.TokenService
import com.softwaret.kpdf.service.user.UserService

class LoginInteractorImpl(
    private val userService: UserService,
    private val tokenService: TokenService
) : LoginInteractor {

    override fun areCredentialsValid(login: Login, password: Password) =
        userService.areCredentialsValid(login, password)

    override fun generateToken(login: Login, password: Password) =
        tokenService.generateToken(login)
}
