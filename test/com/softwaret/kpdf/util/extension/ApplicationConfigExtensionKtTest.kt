@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import io.ktor.config.ApplicationConfig
import io.ktor.config.ApplicationConfigValue
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class ApplicationConfigExtensionKtTest {

    @MockK
    private lateinit var config: ApplicationConfig

    @Test
    fun `stringProperty should return string`() {
        val path = "path"
        val value = "value"
        val property = mockk<ApplicationConfigValue>()

        every { property.getString() } returns value
        every { config.property(path) } returns property

        assertEquals(value, config.stringProperty(path))
    }

    @Test
    fun `stringProperty should return long`() {
        val path = "path"
        val value = 10L
        val property = mockk<ApplicationConfigValue>()

        every { property.getString() } returns value.toString()
        every { config.property(path) } returns property

        assertEquals(value, config.longProperty(path))
    }

    @Test
    fun `algorithm should return algorithm`() {
        val secret = "123"
        val property = mockk<ApplicationConfigValue>()

        every { property.getString() } returns secret
        every { config.property("jwt.SECRET") } returns property

        assertDoesNotThrow { config.algorithm }
    }

    @Test
    fun `issuer should return issuer`() {
        val issuer = "issuer"
        val property = mockk<ApplicationConfigValue>()

        every { property.getString() } returns issuer
        every { config.property("jwt.ISSUER") } returns property

        assertEquals(issuer, config.issuer)
    }

    @Test
    fun `validity should return validity`() {
        val validity = 1000L
        val property = mockk<ApplicationConfigValue>()

        every { property.getString() } returns validity.toString()
        every { config.property("jwt.VALIDITY_MS") } returns property

        assertEquals(validity, config.validity.value)
    }

    @Test
    fun `realm should return realm`() {
        val realm = "realm"
        val property = mockk<ApplicationConfigValue>()

        every { property.getString() } returns realm
        every { config.property("jwt.REALM") } returns property

        assertEquals(realm, config.realm)
    }
}
