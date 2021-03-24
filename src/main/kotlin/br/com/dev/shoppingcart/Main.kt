package br.com.dev.shoppingcart

import br.com.dev.shoppingcart.config.cartControllerModule
import br.com.dev.shoppingcart.config.cartRepositoryModule
import br.com.dev.shoppingcart.config.cartServiceModule
import br.com.dev.shoppingcart.controller.CartController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object Main : KoinComponent {

    private val cartController: CartController by inject()

    fun start() {
        startKoin {
            modules(listOf(cartControllerModule, cartServiceModule, cartRepositoryModule))
        }

        val app = Javalin.create().start(8000)

        app.routes {
            path("cart/:user-id/products") {
                get(cartController::getAllProductsFromCart)
            }
        }
    }

}

fun main() {
    Main.start()
}
