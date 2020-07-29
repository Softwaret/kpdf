package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.repository.validation.input.login.LoginValidator
import com.softwaret.kpdf.repository.validation.input.name.NameValidator
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidator

interface RegisterInteractor :
    LoginValidator,
    NameValidator,
    PasswordValidator {

    fun doesUserExists(login: String): Boolean

    fun registerUser(userTile: UserTile): Boolean

    fun generateToken(userTile: UserTile): String
}