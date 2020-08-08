package com.softwaret.kpdf.db.tables.user

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var login by Users.login
    var password by Users.password
    var name by Users.name
}

fun User.loadFromTile(userTile: UserTile) {
    login = userTile.login
    password = userTile.password
    name = userTile.name
}

fun User.toTile() = UserTile(
    name = name,
    login = login,
    password = password
)