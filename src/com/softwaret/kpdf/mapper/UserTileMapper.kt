package com.softwaret.kpdf.mapper

import com.softwaret.kpdf.db.tables.User
import com.softwaret.kpdf.model.UserTile
import org.jetbrains.exposed.sql.ResultRow

fun toUserTile(row: ResultRow) = UserTile(
    row[User.login],
    row[User.password],
    row[User.name]
)
