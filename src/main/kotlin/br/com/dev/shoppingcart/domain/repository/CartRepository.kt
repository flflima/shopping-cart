package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.CartProduct

class CartRepository {

//    private val allCarts = mutableSetOf(
//        Cart(
//            "1", listOf(Product(1, "Shorts", 55.00, "", "Vestuário"))
//        ),
//        Cart(
//            "2",
//            listOf(
//                Product(1, "Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
//                Product(2, "Camiseta", 80.0, "Camiseta Branca", "Vestuário")
//            )
//        ),
//        Cart("3", emptyList())
//    )

    private val allCarts = mutableSetOf(
        CartProduct("1", 1, 1, 10),
        CartProduct("2", 2, 1, 1),
        CartProduct("2", 2, 2, 1),
    )

    fun getCartByUserId(userId: String): Set<CartProduct> = this.allCarts.filter { it.userId == userId }.toSet()

    fun createCart(userId: String): Set<CartProduct> = this.allCarts.filter { it.userId == userId }.let {
        if (it.isEmpty()) {
            val cart = CartProduct(userId = userId, cartId = (allCarts.size + 1).toLong())
            allCarts.add(cart)
            allCarts.filter { cartProduct -> cartProduct.userId == userId }
        } else
            it.filter { cartProduct -> cartProduct.userId == userId }
    }.toSet()

//    fun addProduct(userId: String, product: Product) {
//        return this.allCarts.firstOrNull { it.userId == userId && product.id == it.productId}.let {
//            if (it == null) {
//                val cart = this.createCart(userId)
//                updateCartProducts(cart, product)
//            } else
//                updateCartProducts(it, product)
//        }
//    }
//
//    fun updateCartProducts(cart: Cart, product: Product) {
//        this.allCarts.remove(cart)
//        val originalProducts = cart.products as MutableList<Product>
//        originalProducts.add(product)
//        this.allCarts.add(cart.copy(userId = cart.userId, products = originalProducts))
//    }

}
