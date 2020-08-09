package com.softwaret.kpdf.db.tables.user

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password

data class UserTile(
    val login: Login,
    val password: Password,
    val name: Name
)
