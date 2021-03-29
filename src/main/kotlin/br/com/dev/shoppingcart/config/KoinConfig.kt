package br.com.dev.shoppingcart.config

import br.com.dev.shoppingcart.contract.CartController
import br.com.dev.shoppingcart.dao.CartRepository
import br.com.dev.shoppingcart.service.CartService
import org.koin.dsl.module

val cartControllerModule = module {
    single { CartController(get() as CartService) }
}

val cartServiceModule = module {
    single { CartService(get() as CartRepository) }
}

val cartRepositoryModule = module {
    single { CartRepository() }
}
