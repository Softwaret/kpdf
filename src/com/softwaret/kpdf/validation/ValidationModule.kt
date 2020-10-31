package com.softwaret.kpdf.validation

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.validation.validators.Validator
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidatorImpl
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidatorImpl
import com.softwaret.kpdf.validation.validators.input.login.LoginValidator
import com.softwaret.kpdf.validation.validators.input.name.NameValidator
import com.softwaret.kpdf.validation.validators.input.password.PasswordValidator
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindValidators() {

    bind<Validator<*>>(tag = Password::class.java) with singleton {
        PasswordValidator(
            BlankValidatorImpl(),
            EmptyValidatorImpl()
        )
    }

    bind<Validator<*>>(tag = Login::class.java) with singleton {
        LoginValidator(
            EmptyValidatorImpl(),
            BlankValidatorImpl()
        )
    }

    bind<Validator<*>>(tag = Name::class.java) with singleton {
        NameValidator(
            BlankValidatorImpl(),
            EmptyValidatorImpl()
        )
    }
}
