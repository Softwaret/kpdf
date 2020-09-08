package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class MillisecondsTest {

    @Test
    fun `milliseconds should keep long value`() {
        val raw = 1000L
        val millis = Milliseconds(raw)

        assertEquals(raw, millis.value)
    }
}
