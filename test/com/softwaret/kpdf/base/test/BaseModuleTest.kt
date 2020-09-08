package com.softwaret.kpdf.base.test

import io.mockk.impl.annotations.RelaxedMockK
import org.kodein.di.DI

abstract class BaseModuleTest {
    @RelaxedMockK
    protected lateinit var builder: DI.MainBuilder

    abstract fun testBind()
}
