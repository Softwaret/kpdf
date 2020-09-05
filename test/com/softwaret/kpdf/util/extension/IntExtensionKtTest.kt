package com.softwaret.kpdf.util.extension

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class IntExtensionKtTest {

    @Test
    fun `toId should return Id`() {
        val raw = 1
        val id = raw.asId()
        assertEquals(raw, id.value)
    }
}
