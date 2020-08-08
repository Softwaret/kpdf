package com.softwaret.kpdf.mapper

import com.softwaret.kpdf.db.tables.user.Users
import com.softwaret.kpdf.model.UserTile
import org.jetbrains.exposed.sql.ResultRow

fun toUserTile(row: ResultRow) = UserTile(
    row[Users.login],
    row[Users.password],
    row[Users.name]
)
