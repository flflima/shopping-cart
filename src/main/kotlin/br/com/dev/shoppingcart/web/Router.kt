package br.com.dev.shoppingcart.web

import br.com.dev.shoppingcart.web.controller.CartController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import org.koin.core.component.KoinComponent

class Router(private val cartController: CartController) {

    fun configure(server: Javalin) {
        server.apply {
            this.routes {
                ApiBuilder.path("cart/:user-id") {
                    ApiBuilder.get(cartController::getCart)

                    ApiBuilder.path("products") {
                        ApiBuilder.get(cartController::getAllProductsFromCart)
                    }
                }
            }
        }
    }
}
