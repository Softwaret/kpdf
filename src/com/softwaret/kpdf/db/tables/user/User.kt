package com.softwaret.kpdf.db.tables.user

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var login by Users.login
    var password by Users.password
    var name by Users.name
}

fun User.toUserTile() = UserTile(
    Login(login),
    Password(password),
    Name(name)
)
