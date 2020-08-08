package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.response.error.ErrorResponseBody

sealed class RegisterErrorResponseBody(message: String) : ErrorResponseBody(message) {

    object CannotRegisterUser : RegisterErrorResponseBody("CannotRegisterUser")

    object LoginAlreadyTaken: RegisterErrorResponseBody("LoginAlreadyTaken")
}