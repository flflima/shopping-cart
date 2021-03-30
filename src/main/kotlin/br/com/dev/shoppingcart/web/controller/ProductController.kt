package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.domain.model.toProductDTO
import br.com.dev.shoppingcart.domain.service.ProductService
import io.javalin.http.Context

class ProductController(private val productService: ProductService) {

    fun getProduct(ctx: Context) {
        productService.getProductById(ctx.pathParam("product-id").toLong()).apply {
            ctx.json(this.toProductDTO())
        }
    }
}
