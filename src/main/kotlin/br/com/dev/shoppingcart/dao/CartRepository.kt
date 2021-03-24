package br.com.dev.shoppingcart.dao

import br.com.dev.shoppingcart.model.Cart
import br.com.dev.shoppingcart.model.Product

class CartRepository {

    private val allCarts = setOf(
        Cart(
            "1", listOf(
                Product("Shorts", 55.00, "", "Vestuário")
            )
        ),
        Cart(
            "2", listOf(
                Product("Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
                Product("Camiseta", 80.0, "Camiseta Branca", "Vestuário")
            )
        ),
        Cart("3", emptyList())
    )

    fun getCartByUserId(userId: String): List<Product> {
        val filtro = allCarts.filter { it.userId == userId }
      
        return if (filtro.isEmpty()) {
            emptyList()
        } else filtro.first().products
    }

//    fun createCart(userId: String): Map<String, Product> {
//        if (getCartByUserId(userId).isEmpty()) {
//            this.carts[userId] = emptyList()
//        }
//
//        return this.carts[userId]
//    }

}