package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.domain.model.toProductDTO
import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.domain.service.ProductService
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.Context

class CartController(private val cartService: CartService, private val productService: ProductService) {

    fun getCart(ctx: Context) {
        this.cartService.getCart(ctx.pathParam("user-id")).apply {
            val cartDTO = CartDTO(this.first().userId, this.toList()
                .filter { cart -> cart.productId != null }
                .map { cartProduct -> productService.getProductById(cartProduct.productId!!).toProductDTO() })
            ctx.json(cartDTO)
        }
    }

    fun createCart(ctx: Context) {
        val cartDTOBody = ctx.body<CartDTO>()
        this.cartService.createCartByUserId(cartDTOBody.userId).apply {
            val cartDTO = CartDTO(this.first().userId)
            ctx.json(cartDTO)
            ctx.status(201)
        }
    }
}