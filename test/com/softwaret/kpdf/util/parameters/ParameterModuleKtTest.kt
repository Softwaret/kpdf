package com.softwaret.kpdf.util.parameters

import com.softwaret.kpdf.base.test.BaseModuleTest
import io.mockk.mockk

class ParameterModuleKtTest : BaseModuleTest() {

    override fun testBind() {
        builder.bindParameters(mockk())
    }
}
