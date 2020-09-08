package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class IdTest {

    @Test
    fun `id should keep string value`() {
        val raw = 1
        val id = Id(raw)

        assertEquals(raw, id.value)
    }
}
