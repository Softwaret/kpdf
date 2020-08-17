package com.softwaret.kpdf.repository.validation.input.password

interface PasswordValidator {

    fun validatePassword(password: String?): PasswordValidationError?
}
