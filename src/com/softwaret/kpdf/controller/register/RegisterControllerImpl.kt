package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.register.RegisterInteractor
import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.response.OK
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.Unauthorized
import com.softwaret.kpdf.response.UnprocessableEntity
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.success.RegisterResponseBody
import com.softwaret.kpdf.util.extension.get
import com.softwaret.kpdf.util.parameters.BodyParameter.*
import io.ktor.http.Parameters

class RegisterControllerImpl(
    private val registerInteractor: RegisterInteractor
) : BaseController(), RegisterController {

    override fun register(parameters: Parameters) = parameters[LOGIN, PASSWORD, NAME]
        .let { (login, password, name) ->

            when {
                isUserDataInvalid(login, password, name) ->
                    Response.UnprocessableEntity(ErrorResponseBody.AuthorizationFailed)

                isUserDataInvalid(login, password, name).not() ->
                    registerUser(login, password, name)

                else -> Response.Unauthorized(ErrorResponseBody.Unknown)
            }
        }

    private fun isUserDataInvalid(login: String?, password: String?, name: String?) =
        login.isNullOrEmpty() || password.isNullOrEmpty() || name.isNullOrEmpty() ||
                registerInteractor.doesUserExists(login)

    private fun registerUser(login: String?, password: String?, name: String?) =
        if (login == null || password == null || name == null) {
            Response.UnprocessableEntity(ErrorResponseBody.CannotRegisterUser)
        } else {
            val isRegistered = registerInteractor.registerUser(UserTile.from(login, password, name))
            if (isRegistered) {
                Response.OK(RegisterResponseBody(registerInteractor.generateToken(login)))
            } else {
                Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)
            }
        }
}