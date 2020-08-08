package com.softwaret.kpdf.controller.register

import com.softwaret.kpdf.controller.base.BaseController
import com.softwaret.kpdf.interactor.register.RegisterInteractor
import com.softwaret.kpdf.response.BadRequest
import com.softwaret.kpdf.response.OK
import com.softwaret.kpdf.response.Response
import com.softwaret.kpdf.response.error.ErrorResponseBody
import com.softwaret.kpdf.response.success.RegisterResponseBody
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.util.extension.areAllNull

class RegisterControllerImpl(
    private val interactor: RegisterInteractor,
    private val inputValidator: InputValidator
) : BaseController(), RegisterController {

    override fun register(login: String, password: String, name: String) =
        when {
            isInputValid(login, password, name).not() ->
                Response.BadRequest(ErrorResponseBody.InputInvalid)

            doesUserExist(login) ->
                Response.BadRequest(RegisterErrorResponseBody.LoginAlreadyTaken)

            else -> {
                registerUser(login, password, name)
                Response.OK(RegisterResponseBody("jwtService.generateToken()"))
            }
        }

    private fun isInputValid(login: String, name: String, password: String) =
        inputValidator.run { areAllNull(validateLogin(login), validateName(name), validatePassword(password)) }

    private fun doesUserExist(login: String) = interactor.doesUserExists(login)

    private fun registerUser(login: String, password: String, name: String) =
        interactor.registerUser(login, password, name)
}
