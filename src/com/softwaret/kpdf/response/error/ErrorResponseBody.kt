package com.softwaret.kpdf.response.error

import com.softwaret.kpdf.response.ResponseBody

abstract class ErrorResponseBody(val errorMessage: String) : ResponseBody {

    object AuthorizationFailed : ErrorResponseBody("AuthorizationFailed")

    object InputInvalid : ErrorResponseBody("InputInvalid")

    object Unknown : ErrorResponseBody("UnknownError")
}
