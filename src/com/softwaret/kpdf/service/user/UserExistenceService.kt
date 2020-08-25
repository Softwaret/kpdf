package com.softwaret.kpdf.service.user

import com.softwaret.kpdf.model.inline.Login

interface UserExistenceService {

    fun doesUserExist(login: Login): Boolean
}
