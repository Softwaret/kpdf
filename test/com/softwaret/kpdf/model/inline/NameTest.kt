package com.softwaret.kpdf.model.inline

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class NameTest {

    @Test
    fun `name should keep string value`() {
        val raw = "asd"
        val name = Name(raw)

        assertEquals(raw, name.value)
    }
}
