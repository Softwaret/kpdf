package com.softwaret.kpdf.controller.login

import ErrorFactory
import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.login.LoginInteractor
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.error.ErrorType
import com.softwaret.kpdf.response.success.LoginResponse
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters

class LoginControllerImpl(
    private val loginInteractor: LoginInteractor,
    errorFactory: ErrorFactory
) : BaseController(errorFactory), LoginController {

    companion object {

        private const val LOGIN = "login"
        private const val PASSWORD = "password"
    }

    override suspend fun login(parameters: Parameters, respond: suspend (Response) -> Unit) {
        val login = parameters[LOGIN]
        val password = parameters[PASSWORD]
        when {
            login.isNullOrEmpty() -> respond(
                Response(HttpStatusCode.UnprocessableEntity, error(ErrorType.NULL_EMPTY_LOGIN))
            )
            password.isNullOrEmpty() ->
                respond(
                    Response(HttpStatusCode.UnprocessableEntity, error(ErrorType.NULL_EMPTY_PASSWORD))
                )
            loginInteractor.doesUserExists(login).not() ->
                respond(
                    Response(HttpStatusCode.NotFound, error(ErrorType.USER_NOT_EXISTS))
                )
            loginInteractor.areCredentialsValid(login, password).not() ->
                respond(
                    Response(
                        HttpStatusCode.Forbidden, error(ErrorType.INVALID_CREDENTIALS)
                    )
                )
            loginInteractor.areCredentialsValid(login, password) ->
                respond(
                    Response(HttpStatusCode.OK, LoginResponse("TOKEN"))
                )
            else -> respond(
                Response(HttpStatusCode.Forbidden, error(ErrorType.UNKNOWN))
            )
        }
    }
}