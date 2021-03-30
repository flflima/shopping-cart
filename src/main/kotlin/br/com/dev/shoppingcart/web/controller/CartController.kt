package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.domain.service.CartService
import io.javalin.http.Context

class CartController(private val cartService: CartService) {

    fun getAllProductsFromCart(ctx: Context) {
        this.cartService.getAllProductsFromCart(ctx.pathParam("user-id")).apply {
            if (this != null) {
                ctx.json(this)
            } else {
                ctx.status(404)
            }
        }
    }

    fun getCart(ctx: Context) {
        this.cartService.getCart(ctx.pathParam("user-id")).apply {
            if (this != null) {
                ctx.json(this)
            } else {
                ctx.status(404)
            }
        }
    }
}