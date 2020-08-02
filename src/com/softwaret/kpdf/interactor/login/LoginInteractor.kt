package com.softwaret.kpdf.interactor.login

interface LoginInteractor {

    fun doesUserExists(login: String): Boolean

    fun areCredentialsValid(login: String, password: String): Boolean

    fun generateToken(login: String): String
}