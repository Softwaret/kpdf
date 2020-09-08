package com.softwaret.kpdf.interactor

import com.softwaret.kpdf.base.test.BaseModuleTest
import org.junit.jupiter.api.Test

internal class InteractorModuleKtTest : BaseModuleTest() {

    @Test
    override fun testBind() {
        builder.bindInteractors()
    }
}
