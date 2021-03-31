package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.toProductDTO
import br.com.dev.shoppingcart.web.dto.CartDTO

object CartMock {

    fun getOneCart() = CartDTO("1")

    fun getOneEmptyCart() = listOf(Cart(1, "1"))

    fun getOneCartWithThreeProducts() = CartDTO("1", ProductMock.getListWithThreeProducts().map { it.toProductDTO() })

}
