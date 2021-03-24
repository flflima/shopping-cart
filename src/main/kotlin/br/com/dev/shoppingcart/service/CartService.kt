package br.com.dev.shoppingcart.service

import br.com.dev.shoppingcart.dao.CartRepository
import br.com.dev.shoppingcart.model.Product

class CartService(private val cartRepository: CartRepository) {

    fun getAllProductsFromCart(userId: String): List<Product> =
        this.cartRepository.getCartByUserId(userId)
}