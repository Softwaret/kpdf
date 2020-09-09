@file:Suppress("EXPERIMENTAL_API_USAGE")

package com.softwaret.kpdf.util.extension

import io.ktor.config.ApplicationConfig
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertTrue

@ExtendWith(MockKExtension::class)
internal class ApplicationConfigExtensionKtTest {

    @MockK
    private lateinit var config: ApplicationConfig

    @Test
    fun `algorithmPath should not be blank`() {
        assertTrue { config.algorithmPath.isBlank().not() }
    }

    @Test
    fun `issuerPath should not be blank`() {
        assertTrue { config.issuerPath.isBlank().not() }
    }

    @Test
    fun `validityPath should not be blank`() {
        assertTrue { config.validityPath.isBlank().not() }
    }

    @Test
    fun `realmPath should not be blank`() {
        assertTrue { config.realmPath.isBlank().not() }
    }
}
