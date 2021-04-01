package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.CartProduct
import br.com.dev.shoppingcart.domain.model.Product

class CartRepository(
    private val carts: MutableList<Cart>,
    private val cartProducts: MutableList<CartProduct>,
    private val products: MutableList<Product>
) {

    fun getCartByUserId(userId: String): List<Cart> = carts.filter { it.userId == userId }

    fun getProductsByCartId(cartId: Long): List<Product> =
        cartProducts.filter { it.cartId == cartId }
            .flatMap { productCart -> products.filter { it.id == productCart.productID } }

    fun createCart(userId: String, productId: Long? = null, quantity: Int = 0): List<Cart> =
        getCartByUserId(userId).let {
            if (it.isEmpty()) {
                val cart = Cart((carts.size + 1).toLong(), userId)
                carts.add(cart)

                if (productId != null) {
                    val cartProduct = CartProduct(cart.id, productId, quantity)
                    cartProducts.add(cartProduct)
                }

                listOf(cart)
            } else {
                it.filter { cartProduct -> cartProduct.userId == userId }
            }
        }

    fun addProduct(userId: String, productId: Long, quantity: Int): List<CartProduct> =
        getCartByUserId(userId).let {
            if (it.isEmpty()) {
                createCart(userId, productId, quantity)
            } else {
                if (cartProducts.none { c -> c.productID == productId }) {
                    val cartProduct = CartProduct(it.first().id, productId, quantity)
                    cartProducts.add(cartProduct)
                } else {
                    cartProducts.forEach { cartProduct -> cartProduct.addQuantity(quantity) }
                }
            }
            return cartProducts.filter { cartProduct -> cartProduct.productID == productId }
        }

}
