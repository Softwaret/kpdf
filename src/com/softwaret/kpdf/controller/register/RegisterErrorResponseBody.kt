package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.response.error.ErrorResponseBody

sealed class RegisterErrorResponseBody(message: String) : ErrorResponseBody(message) {

    object LoginAlreadyTaken : RegisterErrorResponseBody("LoginAlreadyTaken")
}
