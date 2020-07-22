package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.response.OK
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.Unauthorized
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.success.LoginResponseBody
import com.softwaret.kpdf.util.BodyParameter.LOGIN
import com.softwaret.kpdf.util.BodyParameter.PASSWORD
import com.softwaret.kpdf.util.extension.get
import io.ktor.http.Parameters

class LoginControllerImpl(
    private val loginInteractor: LoginInteractor
) : BaseController(), LoginController {

    override suspend fun login(parameters: Parameters) =
        parameters[LOGIN, PASSWORD].let { (login, password) ->

            when {
                isUserDataInvalid(login, password) -> Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)
                areCredentialsValid(login, password) -> Response.OK(LoginResponseBody("TOKEN"))
                else -> Response.Unauthorized(ErrorResponseBody.Unknown)
            }
        }

    private fun areCredentialsValid(login: String?, password: String?) =
        loginInteractor.areCredentialsValid(login!!, password!!)

    private fun isUserDataInvalid(login: String?, password: String?) =
        login == null || password == null || loginInteractor.doesUserExists(login).not() ||
                loginInteractor.areCredentialsValid(login, password).not()
}