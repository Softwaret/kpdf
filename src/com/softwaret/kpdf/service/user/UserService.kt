package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.model.UserTile

interface UserService {

    fun userByLogin(login: String): UserTile?

    fun createUser(user: UserTile): Boolean
}