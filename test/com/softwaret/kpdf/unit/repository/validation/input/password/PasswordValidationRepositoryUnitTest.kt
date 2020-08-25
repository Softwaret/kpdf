package com.softwaret.kpdf.unit.repository.validation.input.password

import com.softwaret.kpdf.model.inline.Password
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationError
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepository
import com.softwaret.kpdf.repository.validation.input.password.PasswordValidationRepositoryImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class PasswordValidationRepositoryUnitTest {

    private val repository: PasswordValidationRepository = PasswordValidationRepositoryImpl()

    @Test
    fun `empty password should be incorrect`() {
        val password = Password("")
        assert(repository.validatePassword(password) == PasswordValidationError.Generic)
    }

    @Test
    fun `not null and not empty password should be correct`() {
        val password = Password("test")
        assertNull(repository.validatePassword(password))
    }
}
