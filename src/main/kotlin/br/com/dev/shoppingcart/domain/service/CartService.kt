package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.Product
import br.com.dev.shoppingcart.domain.model.toCartDTO
import br.com.dev.shoppingcart.domain.repository.CartRepository
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.ConflictResponse
import io.javalin.http.NotFoundResponse

class CartService(private val cartRepository: CartRepository) {

    fun getAllProductsFromCart(userId: String): List<Product> =
        this.cartRepository.getCartByUserId(userId).let {
            return if (it != null) {
                cartRepository.getCartProductsByUserId(userId)
            } else
                throw NotFoundResponse("User not found!")
        }

    fun getCart(userId: String): Cart = this.cartRepository.getCartByUserId(userId).let {
        return it ?: throw NotFoundResponse("Cart not found!")
    }

    fun createCartByUserId(userId: String): CartDTO {
        cartRepository.getCartByUserId(userId).let {
            if (it != null) {
                throw ConflictResponse("Cart already exists!")
            }
            return cartRepository.createCart(userId).toCartDTO()
        }
    }

}