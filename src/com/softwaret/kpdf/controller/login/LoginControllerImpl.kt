package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.BadRequest
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

    override suspend fun login(login: Login, password: Password) =
        when {
            isInputValid(login, password).not() ->
                Response.BadRequest(ErrorResponseBody.InputInvalid)

            userExistsAndPasswordIsValid(login, password).not() ->
                Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)

            else -> {
                val token = loginUser(login, password)
                Response.OK(LoginResponseBody(token))
            }
        }

    private fun isInputValid(login: Login, password: Password) = inputValidator.run {
        areAllNull(validateLogin(login), validatePassword(password))
    }

    private fun userExistsAndPasswordIsValid(login: Login, password: Password) =
        interactor.areCredentialsValid(login, password)

    private fun loginUser(login: Login, password: Password) =
        interactor.generateToken(login, password)
}
