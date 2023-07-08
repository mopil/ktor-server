package com.example.common.koin

import com.example.service.UserService
import org.koin.dsl.module

val dependencyInjections = module {
    single { UserService(get()) }
}
