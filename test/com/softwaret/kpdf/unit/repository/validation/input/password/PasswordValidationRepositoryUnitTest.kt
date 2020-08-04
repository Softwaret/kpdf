package com.softwaret.kpdf.unit.repository.validation.input.password

import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationError
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepository
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepositoryImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class PasswordValidationRepositoryUnitTest {

    private val repository: PasswordValidationRepository = PasswordValidationRepositoryImpl()

    @Test
    fun `empty password should be incorrect`() {
        val password = ""
        assert(repository.validatePassword(password) == PasswordValidationError.Generic)
    }

    @Test
    fun `null password should be incorrect`() {
        val password: String? = null
        assert(repository.validatePassword(password) == PasswordValidationError.Generic)
    }

    @Test
    fun `not null and not empty password should be correct`() {
        val password = "test"
        assertNull(repository.validatePassword(password))
    }
}