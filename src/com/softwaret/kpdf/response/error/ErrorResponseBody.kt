package com.softwaret.kpdf.response.error

import com.softwaret.kpdf.response.ResponseBody


sealed class ErrorResponseBody(val message: String) : ResponseBody {

    object AuthorizationFailed : ErrorResponseBody("AUTORIZATION FAILED")

    object CannotRegisterUser : ErrorResponseBody("CANNOT REGISTER USER WITH GIVEN DATA")

    object Unknown : ErrorResponseBody("UNKNOWN ERROR OCCURRED")
}