package com.softwaret.kpdf.repository.validation.input.name

sealed class NameValidationError {

    object Generic : NameValidationError()
}
