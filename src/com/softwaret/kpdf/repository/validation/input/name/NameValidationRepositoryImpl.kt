package com.softwaret.kpdf.repository.validation.input.name

class NameValidationRepositoryImpl : NameValidationRepository {

    override fun validateName(name: String?) = if (name.isNullOrBlank()) {
        NameValidationError.Generic
    } else {
        null
    }
}