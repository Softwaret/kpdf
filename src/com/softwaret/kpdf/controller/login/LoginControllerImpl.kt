package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.response.OK
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.Unauthorized
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.success.LoginResponseBody
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.util.extension.areAllNull

class LoginControllerImpl(
    private val interactor: LoginInteractor,
    private val inputValidator: InputValidator
) : BaseController(), LoginController {

    override suspend fun login(login: String, password: String) =
        when {
            isInputValid(login, password).not() ->
                Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)

            areCredentialsValid(login, password) ->
                Response.OK(LoginResponseBody("TOKEN"))

            else -> Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)
        }

    private fun isInputValid(login: String, password: String) = inputValidator.run {
        areAllNull(validateLogin(login), validatePassword(password))
    }

    private fun areCredentialsValid(login: String, password: String) =
        interactor.areCredentialsValid(login, password)
}
