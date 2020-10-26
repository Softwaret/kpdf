package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PasswordTest {

    @Test
    fun `password should keep string value`() {
        val raw = "asd"
        val password = Password(raw)

        assertEquals(raw, password.value)
    }
}
