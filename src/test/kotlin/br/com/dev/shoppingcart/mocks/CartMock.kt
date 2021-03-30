package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.domain.model.Cart

object CartMock {

    fun getOneEmptyCart() = Cart("1", emptyList())

    fun getOneCartWithThreeProducts() = Cart("1", ProductMock.getThreeProducts())
}