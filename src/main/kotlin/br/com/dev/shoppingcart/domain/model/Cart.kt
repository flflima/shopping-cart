package br.com.dev.shoppingcart.domain.model

import br.com.dev.shoppingcart.web.dto.CartDTO

data class Cart(val userId: String, val products: List<Product>)

fun Cart.toCartDTO() = CartDTO(this.userId, this.products.map { it.toProductDTO() })
