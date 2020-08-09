package com.softwaret.kpdf.repository.validation.input.name

import com.softwaret.kpdf.model.inline.Name

interface NameValidator {

    fun validateName(name: Name?): NameValidationError?
}
