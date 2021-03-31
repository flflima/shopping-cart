package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.CartProduct

class CartRepository {

    private val allCarts = mutableSetOf(
        CartProduct("1", 1, 1, 10),
        CartProduct("2", 2, 1, 1),
        CartProduct("2", 2, 2, 1),
    )

    fun getCartByUserId(userId: String): Set<CartProduct> = this.allCarts.filter { it.userId == userId }.toSet()

    fun createCart(userId: String, productId: Long? = null, quantity: Int = 0): Set<CartProduct> =
        this.allCarts.filter { it.userId == userId }.let {
            if (it.isEmpty()) {
                val cart = CartProduct(
                    userId = userId,
                    cartId = (allCarts.size + 1).toLong(),
                    productId = productId,
                    quantity = quantity
                )
                allCarts.add(cart)
                allCarts.filter { cartProduct -> cartProduct.userId == userId }
            } else it.filter { cartProduct -> cartProduct.userId == userId }
        }.toSet()

    fun addProduct(userId: String, productId: Long, quantity: Int): List<CartProduct> {
        allCarts.filter { it.userId == userId }
            .apply {
                return if (this.isEmpty()) {
                    createCart(userId, productId, quantity).toList()
                } else {
                    this.filter { c -> c.productId == productId }.forEach {
                        it.addQuantity(quantity)
                    }
                    this
                }
            }
    }

}
