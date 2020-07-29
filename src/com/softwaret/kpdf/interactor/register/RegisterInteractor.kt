package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.UserTile

interface RegisterInteractor {

    fun doesUserExists(login: String): Boolean

    fun registerUser(userTile: UserTile): Boolean

    fun generateToken(userTile: UserTile): String
}