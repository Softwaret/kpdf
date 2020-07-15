package com.softwaret.kpdf.service

import com.softwaret.kpdf.service.user.LoginUserService
import com.softwaret.kpdf.service.user.RegisterUserService
import com.softwaret.kpdf.service.user.UserServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

fun DI.MainBuilder.bindServices() {

    bind<LoginUserService>() with singleton { UserServiceImpl() }

    bind<RegisterUserService>() with singleton { UserServiceImpl() }
}