package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.domain.model.toCartDTO
import br.com.dev.shoppingcart.domain.model.toProductDTO
import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.Context

class CartController(private val cartService: CartService) {

    fun getAllProductsFromCart(ctx: Context) {
        this.cartService.getAllProductsFromCart(ctx.pathParam("user-id")).apply {
            ctx.json(this.map { it.toProductDTO() })
        }
    }

    fun getCart(ctx: Context) {
        this.cartService.getCart(ctx.pathParam("user-id")).apply {
            ctx.json(this.toCartDTO())
        }
    }

    fun createCart(ctx: Context) {
        val cartDTO = ctx.body<CartDTO>()
        this.cartService.createCartByUserId(cartDTO.userId).apply {
            ctx.json(this)
            ctx.status(201)
        }
    }
}