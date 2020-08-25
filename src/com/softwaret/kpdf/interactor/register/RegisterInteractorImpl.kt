package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.service.user.UserService

class RegisterInteractorImpl(
    private val userService: UserService
) : RegisterInteractor {

    override fun doesUserExists(login: Login) =
        userService.doesUserExist(login)

    override fun registerUser(login: Login, password: Password, name: Name) =
        userService.createUser(UserTile(login, password, name))
}
