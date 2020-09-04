package com.softwaret.kpdf.validation.validators.input.password

import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.validation.validators.Validator
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidator
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidator

class PasswordValidator(
    private val blankValidator: BlankValidator,
    private val emptyValidator: EmptyValidator
) : Validator<Password> {

    override fun validate(field: Password) =
        blankValidator.validate(field.value)
}