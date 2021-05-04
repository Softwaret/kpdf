package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.badRequest
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.ok
import com.softwaret.kpdf.response.success.LoginResponseBody
import com.softwaret.kpdf.response.unauthorized
import com.softwaret.kpdf.validation.InputValidator

class LoginControllerImpl(
    private val interactor: LoginInteractor,
    private val inputValidator: InputValidator
) : BaseController(), LoginController {

    override suspend fun login(login: Login, password: Password) =
        when {
            isInputValid(login, password).not() ->
                Response.badRequest(ErrorResponseBody.InputInvalid)

            userExistsAndPasswordIsValid(login, password).not() ->
                Response.unauthorized(ErrorResponseBody.AuthorizationFailed)

            else -> {
                val token = loginUser(login, password)
                Response.ok(LoginResponseBody(token))
            }
        }

    private fun isInputValid(login: Login, password: Password) =
        inputValidator.run { login.isValid() && password.isValid() }

    private fun userExistsAndPasswordIsValid(login: Login, password: Password) =
        interactor.areCredentialsValid(login, password)

    private fun loginUser(login: Login, password: Password) =
        interactor.generateToken(login, password)
}
