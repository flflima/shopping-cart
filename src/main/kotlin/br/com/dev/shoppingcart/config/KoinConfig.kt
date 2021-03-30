package br.com.dev.shoppingcart.config

import br.com.dev.shoppingcart.web.controller.CartController
import br.com.dev.shoppingcart.domain.repository.CartRepository
import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.web.Router
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import org.koin.dsl.module

object KoinConfig {
    private val cartControllerModule = module {
        single { CartController(get() as CartService) }
    }

    private val cartServiceModule = module {
        single { CartService(get() as CartRepository) }
    }

    private val cartRepositoryModule = module {
        single { CartRepository() }
    }

    private val objectMapperModule = module {
        single { GlobalObjectMapper.getObjectMapper() }
    }

    private val routerModule = module {
        single { Router(get()) }
    }

    val listOfModules =
        listOf(cartControllerModule, cartServiceModule, cartRepositoryModule, objectMapperModule, routerModule)
}

object GlobalObjectMapper {
    fun getObjectMapper() = ObjectMapper().apply {
        this.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    }
}