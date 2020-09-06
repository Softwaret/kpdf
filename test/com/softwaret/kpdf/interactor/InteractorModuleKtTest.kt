package com.softwaret.kpdf.interactor

import com.softwaret.kpdf.base.test.BaseModuleTest

internal class InteractorModuleKtTest : BaseModuleTest() {

    override fun testBind() {
        builder.bindInteractors()
    }
}
