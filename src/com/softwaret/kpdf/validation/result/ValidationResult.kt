package com.softwaret.kpdf.validation.result

sealed class ValidationResult {

    object GenericError: ValidationResult()

    object Empty : ValidationResult()

    object Blank : ValidationResult()

    object Valid : ValidationResult()
}