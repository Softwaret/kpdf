package com.softwaret.kpdf.validation.validators.case.empty

import com.softwaret.kpdf.validation.result.ValidationResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class EmptyValidatorImplTest {

    lateinit var emptyValidator: EmptyValidator

    @BeforeEach
    fun setup() {
        emptyValidator = EmptyValidatorImpl()
    }

    @Test
    fun `empty input should result in validation result empty`() {
        val emptyInput = ""
        val result = emptyValidator.validate(emptyInput)
        assertTrue { result == ValidationResult.Empty }
    }

    @Test
    fun `blank input should result in validation result valid`() {
        val blankInput = "         "
        val result = emptyValidator.validate(blankInput)
        assertTrue { result == ValidationResult.Valid }
    }

    @Test
    fun `not empty inpud should result in validation result vaild`() {
        val notEmptyInput = "sadlkk31231231"
        val result = emptyValidator.validate(notEmptyInput)
        assertTrue { result == ValidationResult.Valid }
    }
}
