package com.softwaret.kpdf.repository.validation.input.password

class PasswordValidationRepositoryImpl : PasswordValidationRepository {

    override fun validatePassword(password: String?) = if (password.isNullOrBlank()) {
        PasswordValidationError.Generic
    } else {
        null
    }
}
