package com.softwaret.kpdf.validation.validators.input

import com.softwaret.kpdf.validation.result.ValidationResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class BaseValidatorTest {

    private lateinit var baseValidator: BaseValidator

    @BeforeEach
    fun setup() {
        baseValidator = object : BaseValidator {}
    }

    @Test
    fun `valid validation results inputs should result in empty validation results`() {
        val validationResultInputs = arrayOf(ValidationResult.Valid, ValidationResult.Valid, ValidationResult.Valid)
        val results = baseValidator.validate(*validationResultInputs)
        assertTrue { results.results.isEmpty() }
    }

    @Test
    fun `not valid validation results inputs should result in not empty validation results`() {
        val validationResultInputs = arrayOf(ValidationResult.Empty, ValidationResult.Blank)
        val results = baseValidator.validate(*validationResultInputs)
        assertFalse { results.results.isEmpty() }
    }

    @Test
    fun `mix of valid and not valid validation results inputs should result in not empty validation results`() {
        val validationResultInputs = arrayOf(ValidationResult.Valid, ValidationResult.Valid, ValidationResult.Blank)
        val results = baseValidator.validate(*validationResultInputs)
        assertFalse { results.results.isEmpty() }
    }
}