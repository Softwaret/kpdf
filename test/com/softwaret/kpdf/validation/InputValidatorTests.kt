package com.softwaret.kpdf.validation

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.validation.result.ValidationResult
import com.softwaret.kpdf.validation.validators.Validator
import com.softwaret.kpdf.validation.validators.case.blank.BlankValidatorImpl
import com.softwaret.kpdf.validation.validators.case.empty.EmptyValidatorImpl
import com.softwaret.kpdf.validation.validators.input.password.PasswordValidator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class InputValidatorTests {

    private val di = DI {
        bind<Validator<*>>(tag = Password::class.java) with singleton {
            PasswordValidator(
                BlankValidatorImpl(),
                EmptyValidatorImpl()
            )
        }
    }

    lateinit var inputValidator: InputValidator

    @BeforeEach
    fun setup() {
        inputValidator = InputValidator(di)
    }

    @Test
    fun `validating long should result in generic error because of no available binding defined`() {
        val result = inputValidator.validate(100L)
        assertEquals(ValidationResult.GenericError, result)
    }

    @Test
    fun `validation of empty password should return not empty validation result`() {
        val validationResult = inputValidator.validate(Password(""))
        val isEmpty = validationResult.results.isEmpty()
        assertFalse { isEmpty }
    }

    @Test
    fun `validation result of proper password should return empty validation result`() {
        val validationResult = inputValidator.validate(Password("password"))
        val isEmpty = validationResult.results.isEmpty()
        assertTrue { isEmpty }
    }

    @Test
    fun `validation of empty password should return false`() {
        val password = Password("")
        inputValidator.run {
            val isValid = password.isValid()
            assertFalse { isValid }
        }
    }

    @Test
    fun `validation of proper password should return true`() {
        val password = Password("password")
        inputValidator.run {
            val isValid = password.isValid()
            assertTrue { isValid }
        }
    }

    @Test
    fun `validation of field with no available bindings should return false`() {
        val login = Login("login")
        inputValidator.run {
            val isValid = login.isValid()
            assertFalse { isValid }
        }
    }
}