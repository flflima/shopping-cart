package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.Product

class ProductRepository {

    private val allProducts = mutableSetOf(
        Product(1, "Shorts", 55.00, "", "Vestuário"),
        Product(2, "Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
        Product(3, "Camiseta", 80.0, "Camiseta Branca", "Vestuário")
    )

    fun findProductById(productId: Long) = allProducts.firstOrNull {
        it.id == productId
    }
}