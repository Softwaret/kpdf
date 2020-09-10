package com.softwaret.kpdf.service.validation.input

import com.softwaret.kpdf.repository.validation.input.login.LoginValidator
import com.softwaret.kpdf.repository.validation.input.name.NameValidator
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidator

interface InputValidator :
    PasswordValidator,
    NameValidator,
    LoginValidator
