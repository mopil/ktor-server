package com.example.core.configuration

import com.example.core.util.ApplicationConfigUtils
import com.example.domain.model.ProductRepository
import com.example.domain.model.ProductRepositoryImpl
import com.example.domain.model.UserRepository
import com.example.domain.service.ProductService
import com.example.domain.service.UserService
import com.example.infrastructure.implementation.UserRepositoryExposedImpl
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.SLF4JLogger

val dependencyInjectionModule = module {
    single {
        UserService(
            userRepository = get(),
            jwtSecretKey = ApplicationConfigUtils.getConfigProperty("jwt.secret")
        )
    }
    single<UserRepository> { UserRepositoryExposedImpl() }
    single { ProductService(get()) }
    single<ProductRepository> { ProductRepositoryImpl() }
}

fun Application.configureDependencyInjection() {
    install(Koin) {
        SLF4JLogger()
        modules(dependencyInjectionModule)
    }
}
