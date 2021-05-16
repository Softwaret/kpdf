package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.register.RegisterInteractor
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.badRequest
import com.softwaret.kpdf.response.created
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.success.RegisterResponseBody
import com.softwaret.kpdf.validation.InputValidator

class RegisterControllerImpl(
    private val interactor: RegisterInteractor,
    private val inputValidator: InputValidator
) : BaseController(), RegisterController {

    override fun register(login: Login, password: Password, name: Name) =
        when {
            isInputValid(login, password, name).not() ->
                Response.badRequest(ErrorResponseBody.InputInvalid)

            doesUserExist(login) ->
                Response.badRequest(RegisterErrorResponseBody.LoginAlreadyTaken)

            else -> {
                registerUser(login, password, name)
                Response.created(RegisterResponseBody())
            }
        }

    private fun isInputValid(login: Login, password: Password, name: Name) =
        inputValidator.run { login.isValid() && name.isValid() && password.isValid() }

    private fun doesUserExist(login: Login) = interactor.doesUserExists(login)

    private fun registerUser(login: Login, password: Password, name: Name) =
        interactor.registerUser(login, password, name)
}
