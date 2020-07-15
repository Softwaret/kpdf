package com.softwaret.kpdf.service.user

interface LoginUserService : BaseUserService {

    fun getUserPassword(login: String): String?
}