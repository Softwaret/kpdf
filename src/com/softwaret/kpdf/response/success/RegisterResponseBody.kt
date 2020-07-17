package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.response.ResponseBody

data class RegisterResponseBody(
    val token: String
) : ResponseBody