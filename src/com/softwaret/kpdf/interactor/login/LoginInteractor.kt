package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.repository.validation.input.login.LoginValidator
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidator

interface LoginInteractor : LoginValidator, PasswordValidator {

    fun doesUserExists(login: String): Boolean

    fun areCredentialsValid(login: String, password: String): Boolean

    fun generateToken(userTile: UserTile): String
}