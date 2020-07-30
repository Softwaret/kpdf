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
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.util.extension.areAllNull
import com.softwaret.kpdf.util.extension.get
import com.softwaret.kpdf.util.parameters.BodyParameter.*
import io.ktor.http.Parameters

class RegisterControllerImpl(
    private val interactor: RegisterInteractor,
    private val inputValidator: InputValidator
) : BaseController(), RegisterController {

    override fun register(parameters: Parameters) =
        parameters[LOGIN, PASSWORD, NAME].let { (login, password, name) ->
            when {
                isUserDataValid(login, password, name).not() ->
                    Response.UnprocessableEntity(ErrorResponseBody.AuthorizationFailed)

                isUserDataValid(login, password, name) ->
                    registerUser(login, password, name)

                else -> Response.Unauthorized(ErrorResponseBody.Unknown)
            }
        }

    private fun isUserDataValid(login: String?, password: String?, name: String?) =
        isInputValid(login, name, password) && interactor.doesUserExists(login!!)

    private fun isInputValid(login: String?, name: String?, password: String?) = inputValidator.run {
        areAllNull(validateLogin(login), validateName(name), validatePassword(password))
    }

    private fun registerUser(login: String?, password: String?, name: String?) =
        if (login == null || password == null || name == null) {
            Response.UnprocessableEntity(ErrorResponseBody.CannotRegisterUser)
        } else {
            val isRegistered = interactor.registerUser(UserTile.from(login, password, name))
            if (isRegistered) {
                Response.OK(RegisterResponseBody("jwtService.generateToken()"))
            } else {
                Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)
            }
        }
}