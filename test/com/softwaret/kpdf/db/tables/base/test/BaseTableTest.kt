package com.softwaret.kpdf.db.tables.base.test

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.BeforeEach

abstract class BaseTableTest {
    companion object {
        private const val URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"
        private const val DRIVER = "org.h2.Driver"
    }

    @BeforeEach
    fun setupBeforeEach() {
        Database.connect(URL, driver = DRIVER)
        transaction { createTables() }
    }

    abstract fun createTables()
}
