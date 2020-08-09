package com.softwaret.kpdf.interactor.register

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password

interface RegisterInteractor {

    fun doesUserExists(login: Login): Boolean

    fun registerUser(login: Login, password: Password, name: Name)
}
