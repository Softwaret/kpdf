package com.softwaret.kpdf.validation.validators.input.name

import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.validation.validators.Validator
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidator
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidator

class NameValidator(
    private val blankValidator: BlankValidator,
    private val emptyValidator: EmptyValidator
) : Validator<Name> {

    override fun validate(field: Name) =
        blankValidator.validate(field.value)
}