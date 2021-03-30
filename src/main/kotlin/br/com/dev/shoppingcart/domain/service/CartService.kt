package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.repository.CartRepository
import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.Product

class CartService(private val cartRepository: CartRepository) {

    fun getAllProductsFromCart(userId: String): List<Product>? {
        if (this.cartRepository.getCartByUserId(userId) != null) {
            return this.cartRepository.getCartProductsByUserId(userId)
        }
        return null
    }

    fun getCart(userId: String): Cart? = this.cartRepository.getCartByUserId(userId)
}