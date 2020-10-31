package com.softwaret.kpdf.validation.validators.input

import com.softwaret.kpdf.validation.result.ValidationResult

interface BaseValidator {

    fun validate(vararg validationResult: ValidationResult) =
        ValidationResult.Result.apply {
            addResults(validationResult.filter { it != ValidationResult.Valid })
        }
}
