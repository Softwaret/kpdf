package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.db.tables.user.User
import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.db.tables.user.Users
import com.softwaret.kpdf.db.tables.user.loadFromTile
import com.softwaret.kpdf.mapper.toUserTile
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl : UserService {

    override fun userByLoginOrNull(login: String) =
        transaction {
            Users.select { Users.login eq login }
                .map(::toUserTile)
                .firstOrNull()
        }

    override fun createUser(userTile: UserTile) {
        transaction {
            User.new { loadFromTile(userTile) }
        }
    }
}
