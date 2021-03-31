package br.com.dev.shoppingcart.domain.model

data class CartProduct(val userId: String, val cartId: Long, val productId: Long? = null, var quantity: Int = 0) {

    fun addQuantity(value: Int = 1) = (quantity + value).also { this.quantity = it }
}

