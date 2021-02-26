package com.softwaret.kpdf.validation.validators.input.name

import com.softwaret.kpdf.model.inline.Name
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidatorImpl
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidatorImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class NameValidatorTests {

    lateinit var nameValidator: NameValidator

    @BeforeEach
    fun setup() {
        nameValidator = NameValidator(BlankValidatorImpl(), EmptyValidatorImpl())
    }

    @Test
    fun `proper name should return empty validation results`() {
        val result = nameValidator.validate(Name("name"))
        val isEmpty = result.results.isEmpty()
        assertTrue { isEmpty }
    }

    @Test
    fun `blank name should return not empty validation results`() {
        val result = nameValidator.validate(Name("     "))
        val isEmpty = result.results.isEmpty()
        assertFalse { isEmpty }
    }
}