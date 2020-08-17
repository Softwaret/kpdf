package com.softwaret.kpdf.repository.validation.input.login

interface LoginValidator {

    fun validateLogin(login: String?): LoginValidationError?
}
