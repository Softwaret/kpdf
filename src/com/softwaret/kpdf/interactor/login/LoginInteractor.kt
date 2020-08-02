package com.softwaret.kpdf.interactor.login

import com.softwaret.kpdf.model.UserTile

interface LoginInteractor {

    fun areCredentialsValid(login: String, password: String): Boolean

    fun generateToken(userTile: UserTile): String
}