package com.softwaret.kpdf.response.success

import java.io.Serializable

data class RegisterResponse(
    val token: String
) : Serializable