package com.softwaret.kpdf.controller

import com.softwaret.kpdf.base.test.BaseModuleTest

class ControllerModuleKtTest : BaseModuleTest() {

    override fun testBind() {
        builder.bindControllers()
    }
}
