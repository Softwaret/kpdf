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
        respond(
            when {
                login.isNullOrEmpty() ->
                    Response(
                        HttpStatusCode.UnprocessableEntity,
                        error(ErrorType.NULL_EMPTY_LOGIN)
                    )
                password.isNullOrEmpty() ->
                    Response(
                        HttpStatusCode.UnprocessableEntity,
                        error(ErrorType.NULL_EMPTY_PASSWORD)
                    )
                loginInteractor.doesUserExists(login).not() ->
                    Response(
                        HttpStatusCode.NotFound,
                        error(ErrorType.USER_NOT_EXISTS)
                    )
                loginInteractor.areCredentialsValid(login, password).not() ->
                    Response(
                        HttpStatusCode.Forbidden,
                        error(ErrorType.INVALID_CREDENTIALS)
                    )
                loginInteractor.areCredentialsValid(login, password) ->
                    Response(
                        HttpStatusCode.OK,
                        LoginResponse("TOKEN")
                    )
                else -> Response(
                    HttpStatusCode.Forbidden,
                    error(ErrorType.UNKNOWN)
                )
            }
        )
    }
}