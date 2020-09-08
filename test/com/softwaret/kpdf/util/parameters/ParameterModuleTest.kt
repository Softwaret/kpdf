package com.softwaret.kpdf.util.parameters

import com.softwaret.kpdf.base.test.BaseModuleTest
import io.mockk.mockk
import org.junit.jupiter.api.Test

class ParameterModuleTest : BaseModuleTest() {

    @Test
    override fun testBind() {
        builder.bindParameters(mockk())
    }
}
