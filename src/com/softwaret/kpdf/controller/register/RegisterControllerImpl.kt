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
import com.softwaret.kpdf.util.extension.requireNotNullAndNotBlank

class RegisterControllerImpl(
    private val interactor: RegisterInteractor,
    private val inputValidator: InputValidator
) : BaseController(), RegisterController {

    override fun register(login: String?, password: String?, name: String?) =
        when {
            isInputValid(login, password, name).not() ->
                Response.UnprocessableEntity(ErrorResponseBody.InputInvalid)

            doesUserExist(login).not() ->
                if (registerUser(login, password, name)) {
                    Response.OK(RegisterResponseBody("jwtService.generateToken()"))
                } else {
                    Response.Unauthorized(ErrorResponseBody.CannotRegisterUser)
                }

            else -> Response.Unauthorized(ErrorResponseBody.AuthorizationFailed)
        }

    private fun isInputValid(login: String?, name: String?, password: String?) =
        inputValidator.run { areAllNull(validateLogin(login), validateName(name), validatePassword(password)) }

    private fun doesUserExist(login: String?): Boolean {
        requireNotNullAndNotBlank(login) { "Login should not be null" }

        return interactor.doesUserExists(login)
    }

    private fun registerUser(login: String?, password: String?, name: String?): Boolean {
        requireNotNullAndNotBlank(login) { "Login should not be null" }
        requireNotNullAndNotBlank(password) { "Password should not be null" }
        requireNotNullAndNotBlank(name) { "Name should not be null" }

        return interactor.registerUser(UserTile.from(login, password, name))
    }
}
