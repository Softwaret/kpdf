package com.softwaret.kpdf.db.tables.user

import com.softwaret.kpdf.db.tables.base.test.BaseTableTest
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import kotlin.test.assertNotNull

class UserTest : BaseTableTest() {

    override fun createTables() {
        SchemaUtils.create(Users)
    }

    @Test
    fun `create new user should create user`() {
        transaction {
            val createdUser = User.new {
                login = "login"
                password = "password"
                name = "name"
            }

            assertNotNull(User.findById(createdUser.id))
        }
    }

}