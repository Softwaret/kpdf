package com.softwaret.kpdf.response.success

import com.softwaret.kpdf.response.ResponseBody

data class RefreshTokenResponseBody(val token: String, val refreshToken: String) : ResponseBody
