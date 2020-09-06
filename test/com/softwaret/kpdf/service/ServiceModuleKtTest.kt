package com.softwaret.kpdf.service

import com.softwaret.kpdf.base.test.BaseModuleTest
import io.mockk.mockk

class ServiceModuleKtTest : BaseModuleTest() {

    override fun testBind() {
        builder.bindServices(mockk())
    }
}
