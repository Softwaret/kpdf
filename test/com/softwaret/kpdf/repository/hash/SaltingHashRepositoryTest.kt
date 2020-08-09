package com.softwaret.kpdf.repository.hash

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SaltingHashRepositoryTest {

    @Test
    fun `no salt should hash original string`() {
        val salt = ""
        val input = "asdb"
        val expectedOutput = "1ba28d80572f43983de521229067bb79b805cf6a62546eff877b332d9eb6abe5"

        assertEquals(expectedOutput, SaltingHashRepository(salt).hashString(input))
    }

    @Test
    fun `salt should be preppended to string before hashing`() {
        val salt = "qq"
        val input = "asdb"
        val expectedOutput = "bb2ac3b6b16eb2ac07d9a3c2192561d1d9df38f0cc61bb553f514a113910b120"

        assertEquals(expectedOutput, SaltingHashRepository(salt).hashString(input))
    }

    @Test
    fun `empty string should fail with an exception`() {
        val salt = ""
        val input = ""

        assertThrows<IllegalArgumentException> { SaltingHashRepository(salt).hashString(input) }
    }
}