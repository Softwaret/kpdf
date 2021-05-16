package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.AuthInteractor
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.badRequest
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.ok
import com.softwaret.kpdf.response.success.LoginResponseBody
import com.softwaret.kpdf.response.success.RefreshTokenResponseBody
import com.softwaret.kpdf.response.unauthorized
import com.softwaret.kpdf.validation.InputValidator

class AuthControllerImpl(
    private val interactor: AuthInteractor,
    private val inputValidator: InputValidator
) : BaseController(), AuthController {

    override suspend fun login(login: Login, password: Password) = when {
        isInputValid(login, password).not() ->
            Response.badRequest(ErrorResponseBody.InputInvalid)

        userExistsAndPasswordIsValid(login, password).not() ->
            Response.unauthorized(ErrorResponseBody.AuthorizationFailed)

        else -> {
            val (token, refreshToken) = loginUser(login, password)
            Response.ok(LoginResponseBody(token, refreshToken))
        }
    }

    private fun isInputValid(login: Login, password: Password) =
        inputValidator.run { login.isValid() && password.isValid() }

    private fun userExistsAndPasswordIsValid(login: Login, password: Password) =
        interactor.areCredentialsValid(login, password)

    private fun loginUser(login: Login, password: Password) =
        interactor.generateToken(login, password) to interactor.generateRefreshToken(login)

    override suspend fun refreshToken(login: Login, refreshToken: String) = when {
        isRefreshTokenValid(refreshToken).not() ->
            Response.unauthorized(ErrorResponseBody.AuthorizationFailed)

        else -> {
            val (newToken, newRefreshToken) = generateNewTokens(login, refreshToken)
            Response.ok(RefreshTokenResponseBody(newToken, newRefreshToken))
        }
    }

    private fun isRefreshTokenValid(refreshToken: String): Boolean =
        interactor.isRefreshTokenValid(refreshToken)

    private fun generateNewTokens(login: Login, refreshToken: String): Pair<String, String> =
        interactor.refreshTokens(login, refreshToken)
}
