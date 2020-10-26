package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LoginTest {

    @Test
    fun `login should keep string value`() {
        val raw = "asd"
        val login = Login(raw)

        assertEquals(raw, login.value)
    }
}
