package com.softwaret.kpdf.validation.validators.input

import com.softwaret.kpdf.validation.result.ValidationResult

abstract class BaseValidator {

    protected fun validate(vararg validationResult: ValidationResult) =
        ValidationResult.Result.apply {
            addResults(validationResult.filter { it != ValidationResult.Valid })
        }
}