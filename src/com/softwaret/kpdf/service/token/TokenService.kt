package com.softwaret.kpdf.service.token

import com.softwaret.kpdf.model.inline.Login

interface TokenService {

    fun generateToken(login: Login): String

    fun generateRefreshToken(login: Login): String

    fun isRefreshTokenValid(refreshToken: String): Boolean
}
