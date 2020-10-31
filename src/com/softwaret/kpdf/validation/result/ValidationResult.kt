package com.softwaret.kpdf.validation.result

sealed class ValidationResult {

    val results: List<ValidationResult> = mutableListOf()

    fun addResults(validationResult: List<ValidationResult>) {
        (results as MutableList<ValidationResult>).addAll(validationResult)
    }

    object GenericError : ValidationResult()

    object Empty : ValidationResult()

    object Blank : ValidationResult()

    object Valid : ValidationResult()

    object Result : ValidationResult()
}
