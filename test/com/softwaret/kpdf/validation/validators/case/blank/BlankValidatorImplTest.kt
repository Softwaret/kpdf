package com.softwaret.kpdf.validation.validators.case.blank

import com.softwaret.kpdf.validation.result.ValidationResult
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class BlankValidatorImplTest {

    lateinit var blankValidator: BlankValidator

    @BeforeEach
    fun setup() {
        blankValidator = BlankValidatorImpl()
    }

    @Test
    fun `blank input of whitespaces should result in validation result blank`() {
        val blankInput = "     "
        val result = blankValidator.validate(blankInput)
        assertTrue { result == ValidationResult.Blank }
    }

    @Test
    fun `blank empty input should result in validation result blank`() {
        val blankInput = ""
        val result = blankValidator.validate(blankInput)
        assertTrue { result == ValidationResult.Blank }
    }

    @Test
    fun `not blank input should result in validation valid`() {
        val notBlankInput = "12312nmd,sadlas9083089@  21312"
        val result = blankValidator.validate(notBlankInput)
        assertTrue { result == ValidationResult.Valid }
    }
}