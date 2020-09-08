package com.softwaret.kpdf.controller

import com.softwaret.kpdf.base.test.BaseModuleTest
import org.junit.jupiter.api.Test

internal class ControllerModuleTest : BaseModuleTest() {

    @Test
    override fun testBind() {
        builder.bindControllers()
    }
}
