package com.softwaret.kpdf.unit.service.validation.input

import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepository
import com.softwaret.kpdf.repository.validation.input.name.NameValidationRepository
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepository
import com.softwaret.kpdf.service.validation.input.InputValidator
import com.softwaret.kpdf.service.validation.input.InputValidatorImpl
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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

}