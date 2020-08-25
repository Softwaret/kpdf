package com.softwaret.kpdf.unit.repository.validation.input.login

import com.softwaret.kpdf.model.inline.Login
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationError
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepository
import com.softwaret.kpdf.repository.validation.input.login.LoginValidationRepositoryImpl
import org.junit.jupiter.api.Test
import kotlin.test.assertNull

class LoginValidationRepositoryUnitTest {

    private val repository: LoginValidationRepository = LoginValidationRepositoryImpl()

    @Test
    fun `empty login should be incorrect`() {
        val login = Login("")
        assert(repository.validateLogin(login) == LoginValidationError.Generic)
    }

    @Test
    fun `not null and not empty login should be correct`() {
        val login = Login("test")
        assertNull(repository.validateLogin(login))
    }
}
