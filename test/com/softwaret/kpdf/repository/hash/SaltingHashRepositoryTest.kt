package com.softwaret.kpdf.repository.hash

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class SaltingHashRepositoryTest {

    @Test
    fun `no salt should hash original string`() {
        val salt = ""
        val input = "asdb"
        val expectedOutput = "2848d3b1c577c339be78e8b0c7e9d9c40f7037e6ee20192e7ea0" +
            "ffc7967b08c03cd220514029ae141a645730faa929db63452abce4630b0ca899df3140a91a67"

        assertEquals(expectedOutput, SaltingHashRepository(salt).hash(input))
    }

    @Test
    fun `salt should be preppended to string before hashing`() {
        val salt = "qq"
        val input = "asdb"
        val expectedOutput = "cd73c9dcbcc713cee8ed615d1837a5d12cddfcb764f09a21d0826" +
            "5659ec57ede57a7167e39528e3c36f3a0681f32a8c344846c2658ccf4de113bd90d9abe7d34"

        assertEquals(expectedOutput, SaltingHashRepository(salt).hash(input))
    }

    @Test
    fun `empty string should fail with an exception`() {
        val salt = ""
        val input = ""

        assertThrows<IllegalArgumentException> { SaltingHashRepository(salt).hash(input) }
    }
}
