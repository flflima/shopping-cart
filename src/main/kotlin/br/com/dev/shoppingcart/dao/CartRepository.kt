package br.com.dev.shoppingcart.dao

import br.com.dev.shoppingcart.model.Cart
import br.com.dev.shoppingcart.model.Product

class CartRepository {

    private val allCarts = mutableSetOf(
        Cart(
            "1", listOf(Product("Shorts", 55.00, "", "Vestuário"))
        ),
        Cart(
            "2",
            listOf(
                Product("Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
                Product("Camiseta", 80.0, "Camiseta Branca", "Vestuário")
            )
        ),
        Cart("3", emptyList())
    )

    fun getCartByUserId(userId: String): Cart? =
        this.allCarts.firstOrNull {  it.userId == userId }

    fun getCartProductsByUserId(userId: String): List<Product> =
        this.allCarts.firstOrNull { it.userId == userId }
            .let {
                it?.products ?: emptyList()
            }

    fun createCart(userId: String): Cart {
        return this.allCarts.firstOrNull { it.userId == userId }.let {
            if (it == null) {
                val cart = Cart(userId, emptyList())
                this.allCarts.add(cart)
                cart
            } else
                it
        }
    }

    fun addProduct(userId: String, product: Product) {
        return this.allCarts.firstOrNull { it.userId == userId }.let {
            if (it == null) {
                val cart = this.createCart(userId)
                updateCartProducts(cart, product)
            } else
                updateCartProducts(it, product)
        }
    }

    fun updateCartProducts(cart: Cart, product: Product) {
        this.allCarts.remove(cart)
        val originalProducts = cart.products as MutableList<Product>
        originalProducts.add(product)
        this.allCarts.add(cart.copy(userId = cart.userId, products = originalProducts))
    }

}
