package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.model.CartProduct
import br.com.dev.shoppingcart.domain.repository.CartRepository
import io.javalin.http.ConflictResponse
import io.javalin.http.NotFoundResponse

class CartService(private val cartRepository: CartRepository) {

//    fun getAllProductsFromCart(userId: String): List<Product> =
//        this.cartRepository.getCartByUserId(userId).let {
//            return if (it.isEmpty()) {
//                throw NotFoundResponse("User not found!")
//            } else it.map { cartProduct ->
//                productRepository.findProductById(cartProduct.productId!!)!!
//            }
//        }

    fun getCart(userId: String): Set<CartProduct> = this.cartRepository.getCartByUserId(userId).let {
        return if (it.isEmpty()) throw NotFoundResponse("Cart not found!") else it
    }

    fun createCartByUserId(userId: String): Set<CartProduct> {
        cartRepository.getCartByUserId(userId).let {
            if (it.isNotEmpty()) {
                throw ConflictResponse("Cart already exists!")
            }
            return cartRepository.createCart(userId)
        }
    }

}