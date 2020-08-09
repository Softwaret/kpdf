package com.softwaret.kpdf.repository.validation.input.name

import com.softwaret.kpdf.model.inline.Name

class NameValidationRepositoryImpl : NameValidationRepository {

    override fun validateName(name: Name?) =
        if (name?.value.isNullOrBlank()) {
            NameValidationError.Generic
        } else {
            null
        }
}
