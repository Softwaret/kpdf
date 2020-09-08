package com.softwaret.kpdf.response.success

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.io.Serializable

@Suppress("USELESS_IS_CHECK")
internal class EmptyResponseBodyTest {

    @Test
    fun `EmptyResponseBody should be serializable`() {
        val obj = EmptyResponseBody
        assertTrue(obj is Serializable)
    }
}
