package br.com.dev.shoppingcart.domain.model

data class CartProduct(val cartId: Long, val productID: Long, var quantity: Int = 0) {

    fun addQuantity(value: Int = 1) = (quantity + value).also { this.quantity = it }
}
