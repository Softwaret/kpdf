package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.response.ResponseBody

data class LoginResponseBody(
    val token: String
) : ResponseBody
