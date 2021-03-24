package br.com.dev.shoppingcart.controller

import br.com.dev.shoppingcart.service.CartService
import io.javalin.http.Context

class CartController(private val cartService: CartService) {

    fun getAllProductsFromCart(ctx: Context) {
        ctx.json(this.cartService.getAllProductsFromCart(ctx.pathParam("user-id")))
    }
}