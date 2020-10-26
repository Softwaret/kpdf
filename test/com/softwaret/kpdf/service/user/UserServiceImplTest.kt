package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.base.test.BaseTableTest
import com.softwaret.kpdf.db.tables.user.Users
import org.jetbrains.exposed.sql.SchemaUtils

internal class UserServiceImplTest : BaseTableTest() {

    override fun createTables() {
        SchemaUtils.create(Users)
    }
}
