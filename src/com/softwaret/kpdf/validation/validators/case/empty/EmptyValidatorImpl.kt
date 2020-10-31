package com.softwaret.kpdf.validation.validators.case.empty

import com.softwaret.kpdf.validation.result.ValidationResult

class EmptyValidatorImpl : EmptyValidator {

    override fun validate(field: String) =
        if (field.isEmpty()) {
            ValidationResult.Empty
        } else {
            ValidationResult.Valid
        }
}
