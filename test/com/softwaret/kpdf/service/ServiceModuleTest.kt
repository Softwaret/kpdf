package com.softwaret.kpdf.service

import com.softwaret.kpdf.base.test.BaseModuleTest
import io.mockk.mockk
import org.junit.jupiter.api.Test

class ServiceModuleTest : BaseModuleTest() {

    @Test
    override fun testBind() {
        builder.bindServices(mockk())
    }
}
