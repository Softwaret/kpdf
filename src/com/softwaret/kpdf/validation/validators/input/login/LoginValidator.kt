package com.softwaret.kpdf.validation.validators.input.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.validation.validators.Validator
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidator
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidator
import com.softwaret.kpdf.validation.validators.input.BaseValidator

class LoginValidator(
    private val emptyValidator: EmptyValidator,
    private val blankValidator: BlankValidator
) : BaseValidator, Validator<Login> {

    override fun validate(field: Login) =
        validate(
            emptyValidator.validate(field.value),
            blankValidator.validate(field.value)
        )
}
