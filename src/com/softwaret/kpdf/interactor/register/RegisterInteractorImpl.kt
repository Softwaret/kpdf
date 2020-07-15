package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.service.user.RegisterUserService

class RegisterInteractorImpl(
    private val userService: RegisterUserService
) : RegisterInteractor {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun registerAndAuthenticate(userTile: UserTile) =
        if (userService.createUser(userTile)) {
            "TOKEN"
        } else {
            null
        }
}
