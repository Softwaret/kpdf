package com.softwaret.kpdf.controller.base

import org.junit.jupiter.api.Test

internal class BaseControllerTest {

    @Test
    fun `BaseController object should be creatable`() {
        object : BaseController() {}
    }
}