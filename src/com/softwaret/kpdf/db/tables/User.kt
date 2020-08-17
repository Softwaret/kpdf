package com.softwaret.kpdf.db.tables

import org.jetbrains.exposed.dao.IntIdTable

object User : IntIdTable() {
    private const val LOGIN_LENGTH = 50
    private const val PASSWORD_LENGTH = 50
    private const val NAME_LENGTH = 50

    val login = varchar("login", LOGIN_LENGTH)
    val password = varchar("password", PASSWORD_LENGTH)
    val name = varchar("name", NAME_LENGTH)
}
