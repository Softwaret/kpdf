package com.softwaret.kpdf.interactor.login

class LoginInteractorImpl : LoginInteractor {

    override fun doesUserExists(login: String) = login == "jan"

    override fun areCredentialsValid(login: String, password: String) =
        login == "jan" && password == "jan"
}