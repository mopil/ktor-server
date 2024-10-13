package com.example.core.configuration

import com.example.domain.model.ChatRepository
import com.example.domain.model.ProductRepository
import com.example.domain.model.ProductRepositoryImpl
import com.example.domain.model.UserRepository
import com.example.domain.model.UserRepositoryImpl
import com.example.domain.service.ChatService
import com.example.domain.service.ProductService
import com.example.domain.service.UserService
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
