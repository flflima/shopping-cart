package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.domain.model.CartProduct

object CartMock {

    fun getOneEmptyCart() = setOf(CartProduct(userId = "1", cartId = 1))

    fun getOneCartWithThreeProducts() = setOf(
        CartProduct(userId = "1", cartId = 1, productId = 1),
        CartProduct(userId = "1", cartId = 1, productId = 2),
        CartProduct(userId = "1", cartId = 1, productId = 3)
    )
}