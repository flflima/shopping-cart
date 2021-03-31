package br.com.dev.shoppingcart.domain.model

data class CartProduct(val userId: String, val cartId: Long, val productId: Long? = null, val quantity: Int = 0)

