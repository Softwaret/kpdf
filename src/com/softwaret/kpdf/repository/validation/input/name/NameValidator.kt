package com.softwaret.kpdf.repository.validation.input.name

interface NameValidator {
    fun validateName(name: String?): NameValidationError?
}