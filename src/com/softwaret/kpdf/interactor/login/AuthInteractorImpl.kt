package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.service.token.TokenService
import com.softwaret.kpdf.service.user.UserService

class AuthInteractorImpl(
    private val userService: UserService,
    private val tokenService: TokenService
) : AuthInteractor {

    override fun areCredentialsValid(login: Login, password: Password) =
        userService.areCredentialsValid(login, password)

    override fun generateToken(login: Login, password: Password) =
        tokenService.generateToken(login)

    override fun generateRefreshToken(login: Login) =
        tokenService.generateRefreshToken(login)

    override fun isRefreshTokenValid(refreshToken: String): Boolean =
        tokenService.isRefreshTokenValid(refreshToken)

    override fun refreshTokens(login: Login, refreshToken: String) =
        tokenService.generateToken(login) to tokenService.generateRefreshToken(login)
}
