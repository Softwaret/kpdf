package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.db.tables.User
import com.softwaret.kpdf.mapper.toUserTile
import com.softwaret.kpdf.model.UserTile
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl : UserService {

    override fun userByLogin(login: String) =
        transaction {
            User.select { User.login eq login }
                .map(::toUserTile)
                .firstOrNull()
        }

    override fun createUser(user: UserTile) =
        transaction {
            User.insert {
                it[login] = user.login
                it[password] = user.password
                it[name] = user.name
            }.resultedValues != null
        }
}