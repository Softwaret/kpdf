package com.softwaret.kpdf.controller.login

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.error.ErrorType
import com.softwaret.kpdf.response.error.factory.ErrorFactory
import com.softwaret.kpdf.response.success.LoginResponse
import com.softwaret.kpdf.util.BodyParameters.LOGIN
import com.softwaret.kpdf.util.BodyParameters.PASSWORD
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters

class LoginControllerImpl(
    private val loginInteractor: LoginInteractor,
    errorFactory: ErrorFactory
) : BaseController(errorFactory), LoginController {

    override suspend fun login(parameters: Parameters): Response {
        val login = parameters[LOGIN]
        val password = parameters[PASSWORD]
        return respond(
            when {
                isUserDataInvalid(login, password) ->
                    HttpStatusCode.Unauthorized to error(ErrorType.AUTORIZATION_FAILED)
                loginInteractor.areCredentialsValid(login!!, password!!) ->
                    HttpStatusCode.OK to LoginResponse("TOKEN")
                else -> HttpStatusCode.Unauthorized to error(ErrorType.UNKNOWN)
            }
        )
    }

    private fun isUserDataInvalid(login: String?, password: String?) =
        login == null || password == null || loginInteractor.doesUserExists(login).not() ||
                loginInteractor.areCredentialsValid(login, password).not()
}