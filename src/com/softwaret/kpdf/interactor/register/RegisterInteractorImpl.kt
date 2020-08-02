package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile
import com.softwaret.kpdf.service.token.TokenService
import com.softwaret.kpdf.service.user.UserService

class RegisterInteractorImpl(
    private val userService: UserService,
    private val tokenService: TokenService
) : RegisterInteractor {

    override fun doesUserExists(login: String) =
        userService.userByLogin(login) != null

    override fun registerUser(userTile: UserTile) =
        userService.createUser(userTile)

    override fun generateToken(login: String) =
        tokenService.generateToken(login)
}
