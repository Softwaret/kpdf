package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.service.user.UserService

class RegisterInteractorImpl(
    private val userService: UserService
) : RegisterInteractor {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun registerUser(userTile: UserTile) =
        userService.createUser(userTile)
}
