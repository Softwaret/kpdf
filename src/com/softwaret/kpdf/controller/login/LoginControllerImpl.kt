package com.softwaret.kpdf.controller.login

class LoginControllerImpl() : LoginController {

    override fun sayLogin() = "LOGIN"

    override fun testLogin(login: String, password: String) =
        login == "jan" && password == "1234"
}