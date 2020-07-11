package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.register.RegisterInteractor
import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.error.ErrorType
import com.softwaret.kpdf.response.error.factory.ErrorFactory
import com.softwaret.kpdf.response.success.RegisterResponse
import com.softwaret.kpdf.util.BodyParameters
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import java.io.Serializable

class RegisterControllerImpl(
    private val registerInteractor: RegisterInteractor,
    errorFactory: ErrorFactory
) : BaseController(errorFactory), RegisterController {

    override fun register(parameters: Parameters): Response {
        val login = parameters[BodyParameters.LOGIN]
        val password = parameters[BodyParameters.PASSWORD]
        val name = parameters[BodyParameters.NAME]
        return respond(
            when {
                isUserDataInvalid(login, password, name) ->
                    HttpStatusCode.UnprocessableEntity to error(ErrorType.AUTORIZATION_FAILED)
                isUserDataInvalid(login, password, name).not() ->
                    registerUser(login, password, name)
                else -> HttpStatusCode.Unauthorized to error(ErrorType.UNKNOWN)
            }
        )
    }

    private fun isUserDataInvalid(login: String?, password: String?, name: String?) =
        login.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty() ||
                registerInteractor.doesUserExists(login)

    private fun registerUser(login: String?, password: String?, name: String?): Pair<HttpStatusCode, Serializable> {
        return if (login == null || password == null || name == null) {
            HttpStatusCode.UnprocessableEntity to error(ErrorType.CANNOT_REGISTER_USER)
        } else {
            val token = registerInteractor.registerAndAuthenticate(UserTile.from(login, password, name))
            if (token != null) {
                HttpStatusCode.OK to RegisterResponse(token)
            } else {
                HttpStatusCode.Unauthorized to error((ErrorType.AUTORIZATION_FAILED))
            }
        }
    }
}