package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.db.tables.user.User
import com.softwaret.kpdf.db.tables.user.UserTile
import com.softwaret.kpdf.db.tables.user.Users
import com.softwaret.kpdf.db.tables.user.toUserTile
import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.repository.hash.HashRepository
import org.jetbrains.exposed.sql.transactions.transaction

class UserServiceImpl(
    private val hashRepository: HashRepository
) : UserService {

    override fun areCredentialsValid(login: Login, password: Password) =
        userByLoginOrNull(login)?.password?.value.let {
            it != null && it == password.hash()
        }

    override fun userByLoginOrNull(login: Login): UserTile? =
        transaction {
            User.find { Users.login eq login.value }
                .firstOrNull()
                ?.toUserTile()
        }

    override fun createUser(userTile: UserTile) {
        transaction {
            User.new {
                login = userTile.login.value
                name = userTile.name.value
                password = userTile.password.hash()
            }
        }
    }

    override fun doesUserExist(login: Login) =
        transaction { User.find { Users.login eq login.value }.empty().not() }

    private fun Password.hash() = hashRepository.hash(this.value)
}
