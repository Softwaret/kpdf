package com.softwaret.kpdf.base.test

import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.extension.ExtendWith
import org.kodein.di.DI

@ExtendWith(MockKExtension::class)
abstract class BaseModuleTest {
    @RelaxedMockK
    protected lateinit var builder: DI.MainBuilder

    abstract fun testBind()
}
