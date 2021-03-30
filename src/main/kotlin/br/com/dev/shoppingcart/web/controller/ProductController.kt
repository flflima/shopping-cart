package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.domain.model.toProductDTO
import br.com.dev.shoppingcart.domain.service.ProductService
import br.com.dev.shoppingcart.web.dto.ProductDTO
import io.javalin.http.Context

class ProductController(private val productService: ProductService) {

    fun getProduct(ctx: Context) {
        productService.getProductById(ctx.pathParam("product-id").toLong()).apply {
            ctx.json(this.toProductDTO())
        }
    }

    fun createProduct(ctx: Context) {
        val productDTO = ctx.body<ProductDTO>()
        productService.createProduct(productDTO).apply {
            ctx.json(this)
            ctx.status(201)
        }
    }
}
