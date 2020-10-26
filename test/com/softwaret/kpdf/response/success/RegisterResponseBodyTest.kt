package com.softwaret.kpdf.response.success

import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test

internal class RegisterResponseBodyTest {

    @Test
    fun `RegisterResponseBody should be creatable`() {
        assertDoesNotThrow { RegisterResponseBody() }
    }
}
