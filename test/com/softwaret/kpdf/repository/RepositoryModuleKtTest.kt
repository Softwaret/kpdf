package com.softwaret.kpdf.repository

import com.softwaret.kpdf.base.test.BaseModuleTest

class RepositoryModuleKtTest : BaseModuleTest() {

    override fun testBind() {
        builder.bindRepositories()
    }
}
