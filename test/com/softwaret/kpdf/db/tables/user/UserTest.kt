package com.softwaret.kpdf.db.tables.user

import com.softwaret.kpdf.db.tables.base.test.BaseTableTest
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
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

    @Test
    fun `create new user should fail when missing arguments`() {
        assertThrows<ExposedSQLException> {
            transaction {
                User.new {
                    login = "login"
                    name = "name"
                }
            }
        }
    }

    @Test
    fun `create new user should fail with login over 50 chars`() {
        assertThrows<IllegalStateException> {
            transaction {
                User.new {
                    login = "a".repeat(51)
                    password = "password"
                    name = "name"
                }
            }
        }
    }

    @Test
    fun `create new user should pass with password length equal 200 chars`() {
        transaction {
            val createdUser = User.new {
                login = "login"
                password = "p".repeat(200)
                name = "name"
            }

            assertNotNull(User.findById(createdUser.id))
        }
    }
}