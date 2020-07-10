package com.softwaret.kpdf.response.success

import java.io.Serializable

data class LoginResponse(
    val token: String
) : Serializable