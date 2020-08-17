package com.softwaret.kpdf.unit.repository.validation.input.name

import com.softwaret.kpdf.repository.validation.input.name.NameValidationError
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepository
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepositoryImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class NameValidationRepositoryUnitTest {

    private val repository: NameValidationRepository = NameValidationRepositoryImpl()

    @Test
    fun `empty name should be incorrect`() {
        val name = ""
        assert(repository.validateName(name) == NameValidationError.Generic)
    }

    @Test
    fun `null name should be incorrect`() {
        val name: String? = null
        assert(repository.validateName(name) == NameValidationError.Generic)
    }

    @Test
    fun `not null and not empty name should be correct`() {
        val name = "test"
        assertNull(repository.validateName(name))
    }
}
