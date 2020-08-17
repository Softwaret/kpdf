package com.softwaret.kpdf.repository.validation.input.password

sealed class PasswordValidationError {

    object Generic : PasswordValidationError()
}
