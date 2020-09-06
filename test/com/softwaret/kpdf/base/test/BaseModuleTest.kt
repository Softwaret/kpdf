package com.softwaret.kpdf.base.test

import io.mockk.impl.annotations.RelaxedMockK
import org.junit.jupiter.api.Test
import org.kodein.di.DI

abstract class BaseModuleTest {
    @RelaxedMockK
    protected lateinit var builder: DI.MainBuilder

    @Test
    abstract fun testBind()
}
