package com.softwaret.kpdf.repository.validation.input.login

class LoginValidationRepositoryImpl : LoginValidationRepository {

    override fun validateLogin(login: String?) =
        if (login.isNullOrBlank()) {
            LoginValidationError.Generic
        } else {
            null
        }
}
