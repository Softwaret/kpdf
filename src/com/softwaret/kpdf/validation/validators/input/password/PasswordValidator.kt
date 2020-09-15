package com.softwaret.kpdf.validation.validators.input.password

import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.validation.validators.Validator
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidator
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidator
import com.softwaret.kpdf.validation.validators.input.BaseValidator

class PasswordValidator(
    private val blankValidator: BlankValidator,
    private val emptyValidator: EmptyValidator
) : BaseValidator(), Validator<Password> {

    override fun validate(field: Password) =
        validate(
            blankValidator.validate(field.value),
            emptyValidator.validate(field.value)
        )
}