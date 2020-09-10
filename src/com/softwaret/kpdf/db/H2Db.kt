package com.softwaret.kpdf.db

import com.softwaret.kpdf.db.tables.pdf.Pdfs
import com.softwaret.kpdf.db.tables.publication.Publications
import com.softwaret.kpdf.db.tables.user.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object H2Db {

    private const val URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
    private const val DRIVER = "org.h2.Driver"

    fun init() {
        Database.connect(URL, driver = DRIVER)
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Users, Pdfs, Publications)
        }
    }
}
