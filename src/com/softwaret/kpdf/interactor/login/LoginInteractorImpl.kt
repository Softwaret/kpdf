package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.service.token.TokenService
import com.softwaret.kpdf.service.user.UserService

class LoginInteractorImpl(
    private val userService: UserService,
    private val tokenService: TokenService
) : LoginInteractor {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun areCredentialsValid(login: String, password: String) =
        userService.userByLogin(login)?.password == password

    override fun generateToken(login: String) =
        tokenService.generateToken(login)
}