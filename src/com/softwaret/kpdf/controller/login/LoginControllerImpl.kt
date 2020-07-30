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
import com.softwaret.kpdf.util.extension.get
import com.softwaret.kpdf.util.parameters.BodyParameter.LOGIN
import com.softwaret.kpdf.util.parameters.BodyParameter.PASSWORD
import io.ktor.http.Parameters

class LoginControllerImpl(
    private val interactor: LoginInteractor,
    private val inputValidator: InputValidator
) : BaseController(), LoginController {

    override suspend fun login(parameters: Parameters) =
        parameters[LOGIN, PASSWORD].let { (login, password) ->
            when {
                isInputAndUserDataValid(login, password).not() ->
                    Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)

                areCredentialsValid(login, password) ->
                    Response.OK(LoginResponseBody("TOKEN"))

                else -> Response.Unauthorized(ErrorResponseBody.Unknown)
            }
        }

    private fun areCredentialsValid(login: String?, password: String?) =
        interactor.areCredentialsValid(login!!, password!!)

    private fun isInputAndUserDataValid(login: String?, password: String?) =
        isInputValid(login, password) && isUserDataValid(login!!, password!!)

    private fun isUserDataValid(login: String, password: String) =
        interactor.run { doesUserExists(login) && areCredentialsValid(login, password) }

    private fun isInputValid(login: String?, password: String?) = inputValidator.run {
        areAllNull(validateLogin(login), validatePassword(password))
    }
}