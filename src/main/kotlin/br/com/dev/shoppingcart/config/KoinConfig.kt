package br.com.dev.shoppingcart.config

import br.com.dev.shoppingcart.domain.repository.CartRepository
import br.com.dev.shoppingcart.domain.repository.ProductRepository
import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.domain.service.ProductService
import br.com.dev.shoppingcart.web.Router
import br.com.dev.shoppingcart.web.controller.CartController
import br.com.dev.shoppingcart.web.controller.ProductController
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.koin.dsl.module

object KoinConfig {
    private val controllersModules = module {
        single { CartController(get()) }
        single { ProductController(get()) }
    }

    private val servicesModules = module {
        single { CartService(get()) }
        single { ProductService(get()) }
    }

    private val repositoriesModules = module {
        single { CartRepository() }
        single { ProductRepository() }
    }

    private val objectMapperModule = module {
        single { GlobalObjectMapper.getObjectMapper() }
    }

    private val routerModule = module {
        single { Router(get(), get()) }
    }

    val listOfModules =
        listOf(controllersModules, servicesModules, repositoriesModules, objectMapperModule, routerModule)
}

object GlobalObjectMapper {
    fun getObjectMapper() = jacksonObjectMapper().apply {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
    }
}