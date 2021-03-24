package br.com.dev.shoppingcart.config

import br.com.dev.shoppingcart.controller.CartController
import br.com.dev.shoppingcart.dao.CartRepository
import br.com.dev.shoppingcart.service.CartService
import org.koin.dsl.module

val cartControllerModule = module {
    single { CartController(get()) }
}

val cartServiceModule = module {
    single { CartService(get()) }
}

val cartRepositoryModule = module {
    single { CartRepository() }
}