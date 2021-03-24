package br.com.dev.shoppingcart

import br.com.dev.shoppingcart.controller.CartController
import br.com.dev.shoppingcart.dao.CartRepository
import br.com.dev.shoppingcart.service.CartService
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path

fun main() {
    val app = Javalin.create().start(8000)

    val cartController = CartController(CartService(CartRepository()))

    app.routes {
        path("cart/:user-id/products") {
            get(cartController::getAllProductsFromCart)
        }
    }
}