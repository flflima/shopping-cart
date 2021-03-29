package br.com.dev.shoppingcart.contract

import br.com.dev.shoppingcart.service.CartService
import io.javalin.http.Context

class CartController(private val cartService: CartService) {

    fun getAllProductsFromCart(ctx: Context) {
        val allProducts = this.cartService.getAllProductsFromCart(ctx.pathParam("user-id"))

        if (allProducts != null) {
            ctx.json(allProducts)
        } else
            ctx.status(404)
    }

    fun getCart(ctx: Context) {
        val cart = this.cartService.getCart(ctx.pathParam("user-id"))

        if (cart != null) {
            ctx.json(cart)
        } else {
            ctx.status(404)
        }
    }
}