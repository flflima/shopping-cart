package br.com.dev.shoppingcart.web

import br.com.dev.shoppingcart.web.controller.CartController
import br.com.dev.shoppingcart.web.controller.ProductController
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.apibuilder.ApiBuilder.post

class Router(private val cartController: CartController, private val productController: ProductController) {

    fun configure(server: Javalin) {
        server.apply {
            this.routes {
                path("cart") {
                    post(cartController::createCart)

                    path(":user-id") {
                        get(cartController::getCart)

                        path("products") {
                            get(cartController::getAllProductsFromCart)
                        }
                    }
                }

                path("product") {
                    path(":product-id") {
                        get(productController::getProduct)
                    }
                }
            }
        }
    }
}
