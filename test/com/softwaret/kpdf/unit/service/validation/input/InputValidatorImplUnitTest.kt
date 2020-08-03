package com.softwaret.kpdf.unit.service.validation.input

import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepository
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepository
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepository
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.service.validation.input.InputValidatorImpl
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class InputValidatorImplUnitTest {

    @RelaxedMockK
    lateinit var loginRepositoryMock: LoginValidationRepository

    @RelaxedMockK
    lateinit var passwordRepositoryMock: PasswordValidationRepository

    @RelaxedMockK
    lateinit var nameRepositoryMock: NameValidationRepository

    lateinit var validator: InputValidator

    @BeforeEach
    fun setupMocks() {
        validator = InputValidatorImpl(loginRepositoryMock, passwordRepositoryMock, nameRepositoryMock)
    }

    @Test
    fun `validate name should pass call to repository`() {
        val name = "test"

        validator.validateName(name)

        verify(exactly = 1) { nameRepositoryMock.validateName(name) }
    }

    @Test
    fun `validate name should return value from repository`() {
        val name = "test"
        val returnValue = null

        every { nameRepositoryMock.validateName(name) } returns returnValue

        assertEquals(returnValue, validator.validateName(name))
    }

    @Test
    fun `validate login should pass call to repository`() {
        val login = "test"

        validator.validateLogin(login)

        verify(exactly = 1) { loginRepositoryMock.validateLogin(login) }
    }

    @Test
    fun `validate login should return value from repository`() {
        val login = "test"
        val returnValue = null

        every { loginRepositoryMock.validateLogin(login) } returns returnValue

        assertEquals(returnValue, validator.validateLogin(login))
    }

    @Test
    fun `validate password should pass call to repository`() {
        val password = "test"

        validator.validatePassword(password)

        verify(exactly = 1) { passwordRepositoryMock.validatePassword(password) }
    }

    @Test
    fun `validate password should return value from repository`() {
        val password = "test"
        val returnValue = null

        every { passwordRepositoryMock.validatePassword(password) } returns returnValue

        assertEquals(returnValue, validator.validatePassword(password))
    }
}