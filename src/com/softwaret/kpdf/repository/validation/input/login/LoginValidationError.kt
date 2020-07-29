package com.softwaret.kpdf.repository.validation.input.login

sealed class LoginValidationError {
    object Generic : LoginValidationError()
}