package com.softwaret.kpdf.interactor.register

interface RegisterInteractor {

    fun doesUserExists(login: String): Boolean

    fun registerUser(login: String, password: String, name: String)
}
