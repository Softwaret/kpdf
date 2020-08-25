package com.softwaret.kpdf.repository.validation.input.login

import com.softwaret.kpdf.model.inline.Login

class LoginValidationRepositoryImpl : LoginValidationRepository {

    override fun validateLogin(login: Login) =
        if (login.value.isBlank()) {
            LoginValidationError.Generic
        } else {
            null
        }
}
