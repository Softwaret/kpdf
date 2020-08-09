package com.softwaret.kpdf.repository.validation.input.password

import com.softwaret.kpdf.model.inline.Password

class PasswordValidationRepositoryImpl : PasswordValidationRepository {

    override fun validatePassword(password: Password?) =
        if (password?.value.isNullOrBlank()) {
            PasswordValidationError.Generic
        } else {
            null
        }
}
