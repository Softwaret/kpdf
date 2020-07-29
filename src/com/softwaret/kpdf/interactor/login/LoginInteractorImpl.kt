package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.repository.validation.input.login.LoginValidator
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidator
import com.softwaret.kpdf.service.user.UserService
import com.softwaret.kpdf.service.validation.input.InputValidationService

class LoginInteractorImpl(
    private val userService: UserService,
    inputValidationService: InputValidationService
) : LoginInteractor,
    LoginValidator by inputValidationService,
    PasswordValidator by inputValidationService {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun areCredentialsValid(login: String, password: String) =
        userService.userByLogin(login)?.password == password

    override fun generateToken(userTile: UserTile): String {
        TODO("Not yet implemented")
    }
}