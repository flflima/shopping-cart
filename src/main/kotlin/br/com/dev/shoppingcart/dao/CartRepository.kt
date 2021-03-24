package br.com.dev.shoppingcart.dao

import br.com.dev.shoppingcart.model.Product

class CartRepository {

    private val carts = mapOf(
        "1" to listOf(
            Product("Shorts", 55.00, "", "Vestuário")
        ),
        "2" to listOf(
            Product("Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
            Product("Camiseta", 80.0, "Camiseta Branca", "Vestuário")
        ), "3" to emptyList()
    )

    fun getCartByUserId(userId: String): List<Product> =
        if (carts.containsKey(userId)) {
            carts[userId]!!
        } else {
            emptyList()
        }

}