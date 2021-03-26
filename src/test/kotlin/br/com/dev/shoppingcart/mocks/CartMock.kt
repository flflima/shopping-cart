package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.model.Cart

object CartMock {

    fun getOneEmptyCart() = Cart("1", emptyList())
}