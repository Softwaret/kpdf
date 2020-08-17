package com.softwaret.kpdf.service.validation.input

import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepository
import com.softwaret.kpdf.repository.validation.input.login.LoginValidator
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepository
import com.softwaret.kpdf.repository.validation.input.name.NameValidator
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepository
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidator

class InputValidatorImpl(
    loginValidationRepository: LoginValidationRepository,
    passwordValidationRepository: PasswordValidationRepository,
    usernameValidationRepository: NameValidationRepository
) : InputValidator,
    LoginValidator by loginValidationRepository,
    PasswordValidator by passwordValidationRepository,
    NameValidator by usernameValidationRepository