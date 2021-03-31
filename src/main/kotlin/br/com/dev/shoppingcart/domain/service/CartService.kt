package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.model.CartProduct
import br.com.dev.shoppingcart.domain.model.toProductDTO
import br.com.dev.shoppingcart.domain.repository.CartRepository
import br.com.dev.shoppingcart.domain.repository.ProductRepository
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.ConflictResponse
import io.javalin.http.NotFoundResponse

class CartService(private val cartRepository: CartRepository, private val productRepository: ProductRepository) {

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

    fun addProduct(userId: String, productId: Long, quantity: Int): CartDTO {
        productRepository.findProductById(productId).let {
            if (it != null) {
                return cartRepository.addProduct(userId, productId, quantity).let { cartProducts ->
                    CartDTO(cartProducts.first().userId, cartProducts.map { _ -> it.toProductDTO(quantity) })
                }
            }
            throw NotFoundResponse("Product not found!")
        }

    }
}