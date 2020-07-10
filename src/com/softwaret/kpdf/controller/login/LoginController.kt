package com.softwaret.kpdf.controller.login

interface LoginController {

    fun sayLogin(): String

    fun testLogin(login: String, password: String) : Boolean
}