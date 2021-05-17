package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password

interface AuthInteractor {

    fun areCredentialsValid(login: Login, password: Password): Boolean

    fun generateToken(login: Login, password: Password): String

    fun generateRefreshToken(login: Login): String

    fun isRefreshTokenValid(refreshToken: String): Boolean

    fun refreshTokens(login: Login, refreshToken: String): Pair<String, String>
}
