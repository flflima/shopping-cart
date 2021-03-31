package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.database.Database.allCarts
import br.com.dev.shoppingcart.domain.database.Database.allProducts
import br.com.dev.shoppingcart.domain.database.Database.allProductsCart
import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.CartProduct

class CartRepository {

    fun getCartByUserId(userId: String): List<Cart> = allCarts.filter { it.userId == userId }
    
    fun getProductsByCartId(cartId: Long) =
        allProductsCart.filter { it.cartId == cartId }
            .flatMap { productCart -> allProducts.filter { it.id == productCart.productID } }

    fun createCart(userId: String, productId: Long? = null, quantity: Int = 0): List<Cart> =
        getCartByUserId(userId).let {
            if (it.isEmpty()) {
                val cart = Cart((allCarts.size + 1).toLong(), userId)
                allCarts.add(cart)

                if (productId != null) {
                    val cartProduct = CartProduct(cart.id, productId, quantity)
                    allProductsCart.add(cartProduct)
                }

                listOf(cart)
            } else {
                it.filter { cartProduct -> cartProduct.userId == userId }
            }
        }

    fun addProduct(userId: String, productId: Long, quantity: Int): List<Cart> =
        getCartByUserId(userId).let {
            if (it.isEmpty()) {
                createCart(userId, productId, quantity)
            } else {
                allProductsCart.filter { c -> c.productID == productId }
                    .forEach { cartProduct -> cartProduct.addQuantity(quantity) }
                it
            }
        }

}
