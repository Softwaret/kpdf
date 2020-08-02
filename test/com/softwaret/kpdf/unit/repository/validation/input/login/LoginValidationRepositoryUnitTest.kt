package com.softwaret.kpdf.unit.repository.validation.input.login

import com.softwaret.kpdf.repository.validation.input.login.LoginValidationError
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepository
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepositoryImpl
import org.junit.Test
import kotlin.test.assertNull


class LoginValidationRepositoryUnitTest {

    private val repository: LoginValidationRepository = LoginValidationRepositoryImpl()

    @Test
    fun `empty login should be incorrect`() {
        val login = ""
        assert(repository.validateLogin(login) == LoginValidationError.Generic)
    }

    @Test
    fun `null login should be incorrect`() {
        val login: String? = null
        assert(repository.validateLogin(login) == LoginValidationError.Generic)
    }

    @Test
    fun `not null and not empty login should be correct`() {
        val login = "test"
        assertNull(repository.validateLogin(login))
    }
}