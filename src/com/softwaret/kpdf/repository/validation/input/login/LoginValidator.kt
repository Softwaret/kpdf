package com.softwaret.kpdf.repository.validation.input.login

import com.softwaret.kpdf.model.inline.Login

interface LoginValidator {

    fun validateLogin(login: Login): LoginValidationError?
}
