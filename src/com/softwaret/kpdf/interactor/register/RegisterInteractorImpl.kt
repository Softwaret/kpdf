package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.repository.validation.input.login.LoginValidator
import com.softwaret.kpdf.repository.validation.input.name.NameValidator
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidator
import com.softwaret.kpdf.service.user.UserService
import com.softwaret.kpdf.service.validation.input.InputValidationService

class RegisterInteractorImpl(
    private val userService: UserService,
    inputValidationService: InputValidationService
) : RegisterInteractor,
    LoginValidator by inputValidationService,
    PasswordValidator by inputValidationService,
    NameValidator by inputValidationService {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun registerUser(userTile: UserTile) =
        userService.createUser(userTile)

    override fun generateToken(userTile: UserTile): String {
        TODO("Not yet implemented")
    }
}
