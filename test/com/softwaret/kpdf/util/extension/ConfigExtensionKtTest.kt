@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import io.ktor.config.ApplicationConfig
import io.ktor.config.ApplicationConfigValue
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
internal class ConfigExtensionKtTest {

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
}
