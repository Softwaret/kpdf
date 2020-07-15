package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.service.user.LoginUserService

class LoginInteractorImpl(
    private val userService: LoginUserService
) : LoginInteractor {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun areCredentialsValid(login: String, password: String) =
        userService.getUserPassword(login) == password
}