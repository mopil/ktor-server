package com.example.common.config

import com.example.model.domain.ChatRepository
import com.example.model.domain.ProductRepository
import com.example.model.domain.ProductRepositoryImpl
import com.example.model.domain.UserRepository
import com.example.model.domain.UserRepositoryImpl
import com.example.service.ChatService
import com.example.service.ProductService
import com.example.service.UserService
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

val dependencyInjectionModule = module {
    single { UserService(get()) }
    single<UserRepository> { UserRepositoryImpl() }
    single { ProductService(get()) }
    single<ProductRepository> { ProductRepositoryImpl() }
    single { ChatService(get()) }
    single { ChatRepository() }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        SLF4JLogger()
        modules(dependencyInjectionModule)
    }
}
