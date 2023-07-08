package com.example.common.config

import com.example.model.domain.UserRepository
import com.example.model.domain.UserRepositoryImpl
import com.example.service.UserService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

val dependencyInjectionModule = module(createdAtStart = true) {
    single { UserService(get()) }
    single<UserRepository> { UserRepositoryImpl() }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        SLF4JLogger()
        modules(dependencyInjectionModule)
    }
}


