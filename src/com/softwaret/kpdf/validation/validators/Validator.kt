package com.softwaret.kpdf.validation.validators

import com.softwaret.kpdf.validation.result.ValidationResult

interface Validator<T> {

    fun validate(field: T): ValidationResult
}

