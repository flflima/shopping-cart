package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.domain.service.ProductService
import br.com.dev.shoppingcart.web.dto.AddProductDTO
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.Context

class CartController(private val cartService: CartService, private val productService: ProductService) {

    fun getCart(ctx: Context) {
        this.cartService.getCart(ctx.pathParam("user-id")).apply {
            ctx.json(this)
        }
    }

    fun createCart(ctx: Context) {
        val cartDTOBody = ctx.body<CartDTO>()
        this.cartService.createCartByUserId(cartDTOBody.userId).apply {
            ctx.json(this)
            ctx.status(201)
        }
    }

    fun addProduct(ctx: Context) {
        val productDTO = ctx.body<AddProductDTO>()
        this.cartService.addProduct(ctx.pathParam("user-id"), productDTO.id, productDTO.quantity).apply {
            ctx.json(this)
            ctx.status(201)
        }
    }
}