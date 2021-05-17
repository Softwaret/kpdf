package com.softwaret.kpdf.response.success

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LoginResponseBodyTest {

    @Test
    fun `LoginResponseBody should be creatable`() {
        val token = "123"
        val refreshToken = "123"
        val body = LoginResponseBody(token, refreshToken)

        assertEquals(token, body.token)
        assertEquals(refreshToken, body.refreshToken)
    }
}
