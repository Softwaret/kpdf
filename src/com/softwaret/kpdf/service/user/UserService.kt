package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.db.tables.user.UserTile


interface UserService {

    fun userByLoginOrNull(login: String): UserTile?

    fun createUser(userTile: UserTile)
}
