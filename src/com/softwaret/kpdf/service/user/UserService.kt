package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password

interface UserService : UserExistenceService {

    fun areCredentialsValid(login: Login, password: Password): Boolean

    fun userByLoginOrNull(login: Login): UserTile?

    fun createUser(userTile: UserTile)
}
