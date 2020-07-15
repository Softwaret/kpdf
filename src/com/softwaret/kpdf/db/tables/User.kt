package com.softwaret.kpdf.db.tables

import org.jetbrains.exposed.dao.IntIdTable

object User : IntIdTable() {
    val login = varchar("login", 50)
    val password = varchar("password", 50)
    val name = varchar("name", 100)
}