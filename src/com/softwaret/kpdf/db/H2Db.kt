package com.softwaret.kpdf.db

import com.softwaret.kpdf.db.tables.User
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object Db {

    private const val URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private const val DRIVER = "org.h2.Driver"

    fun init() {
        Database.connect(URL, driver = DRIVER)
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(User)
        }
    }
}