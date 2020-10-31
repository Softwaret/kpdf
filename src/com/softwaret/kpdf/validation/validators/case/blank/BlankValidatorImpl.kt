package com.softwaret.kpdf.validation.validators.case.blank

import com.softwaret.kpdf.validation.result.ValidationResult

class BlankValidatorImpl : BlankValidator {

    override fun validate(field: String) =
        if (field.isBlank()) {
            ValidationResult.Blank
        } else {
            ValidationResult.Valid
        }
}
