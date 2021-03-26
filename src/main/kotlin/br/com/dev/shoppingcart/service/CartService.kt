package br.com.dev.shoppingcart.service

import br.com.dev.shoppingcart.dao.CartRepository
import br.com.dev.shoppingcart.model.Cart
import br.com.dev.shoppingcart.model.Product

class CartService(private val cartRepository: CartRepository) {

    fun getAllProductsFromCart(userId: String): List<Product>? {
        if (this.cartRepository.getCartByUserId(userId) != null) {
            return this.cartRepository.getCartProductsByUserId(userId)
        }
        return null
    }

    fun getCart(userId: String): Cart? = this.cartRepository.getCartByUserId(userId)
}