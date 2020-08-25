package com.softwaret.kpdf.repository.validation.input.password

import com.softwaret.kpdf.model.inline.Password

interface PasswordValidator {

    fun validatePassword(password: Password): PasswordValidationError?
}
