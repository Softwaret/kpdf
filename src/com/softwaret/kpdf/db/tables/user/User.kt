package com.softwaret.kpdf.db.tables.user

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.sql.transactions.transaction

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

fun User.Companion.doesUserExist(login: Login) =
    transaction { find { Users.login eq login.value }.empty().not() }
