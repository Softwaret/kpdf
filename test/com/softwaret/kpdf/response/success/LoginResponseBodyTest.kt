package com.softwaret.kpdf.response.success

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LoginResponseBodyTest {

    @Test
    fun `LoginResponseBody should be creatable`() {
        val raw = "123"
        val body = LoginResponseBody(raw)

        assertEquals(raw, body.token)
    }
}
