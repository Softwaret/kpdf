package com.softwaret.kpdf.validation.validators.input.passowrd

import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidatorImpl
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidatorImpl
import com.softwaret.kpdf.validation.validators.input.password.PasswordValidator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PasswordValidatorTests {

    lateinit var passwordValidator: PasswordValidator

    @BeforeEach
    fun setup() {
        passwordValidator = PasswordValidator(BlankValidatorImpl(), EmptyValidatorImpl())
    }

    @Test
    fun `proper password should return empty validation results`() {
        val result = passwordValidator.validate(Password("password"))
        val isEmpty = result.results.isEmpty()
        assertTrue { isEmpty }
    }

    @Test
    fun `blank password should return not empty validation results`() {
        val result = passwordValidator.validate(Password("     "))
        val isEmpty = result.results.isEmpty()
        assertFalse { isEmpty }
    }
}
