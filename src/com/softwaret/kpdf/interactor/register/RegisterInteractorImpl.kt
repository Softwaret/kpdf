package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.service.user.UserService

class RegisterInteractorImpl(
    private val userService: UserService
) : RegisterInteractor {

    override fun doesUserExists(login: String) =
        userService.userByLoginOrNull(login) != null

    override fun registerUser(login: String, password: String, name: String) =
        userService.createUser(UserTile(login = login, password = password, name = name))
}
