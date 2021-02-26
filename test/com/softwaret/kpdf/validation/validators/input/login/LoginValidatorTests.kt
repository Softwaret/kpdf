package com.softwaret.kpdf.validation.validators.input.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidatorImpl
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidatorImpl
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class LoginValidatorTests {

    lateinit var loginValidator: LoginValidator

    @BeforeEach
    fun setup() {
        loginValidator = LoginValidator(EmptyValidatorImpl(), BlankValidatorImpl())
    }

    @Test
    fun `proper login should return empty validation results`() {
        val result = loginValidator.validate(Login("login"))
        val isEmpty = result.results.isEmpty()
        assertTrue { isEmpty }
    }

    @Test
    fun `blank login should return not empty validation results`() {
        val result = loginValidator.validate(Login("     "))
        val isEmpty = result.results.isEmpty()
        assertFalse { isEmpty }
    }
}