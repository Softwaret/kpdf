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
import com.softwaret.kpdf.util.extension.requireNotNullAndNotBlank

class LoginControllerImpl(
    private val interactor: LoginInteractor,
    private val inputValidator: InputValidator
) : BaseController(), LoginController {

    override suspend fun login(login: String?, password: String?) =
        when {
            isInputValid(login, password).not() ->
                Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)

            areCredentialsValid(login, password) ->
                Response.OK(LoginResponseBody("TOKEN"))

            else -> Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)
        }

    private fun isInputValid(login: String?, password: String?) = inputValidator.run {
        areAllNull(validateLogin(login), validatePassword(password))
    }

    private fun areCredentialsValid(login: String?, password: String?): Boolean {
        requireNotNullAndNotBlank(login) { "Login should not be null" }
        requireNotNullAndNotBlank(password) { "Password should not be null" }

        return interactor.areCredentialsValid(login, password)
    }
}