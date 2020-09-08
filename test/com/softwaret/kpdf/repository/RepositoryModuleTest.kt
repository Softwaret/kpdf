package com.softwaret.kpdf.repository

import com.softwaret.kpdf.base.test.BaseModuleTest
import org.junit.jupiter.api.Test

class RepositoryModuleTest : BaseModuleTest() {

    @Test
    override fun testBind() {
        builder.bindRepositories()
    }
}
