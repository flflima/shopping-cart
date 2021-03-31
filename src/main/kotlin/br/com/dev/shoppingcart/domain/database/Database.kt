package br.com.dev.shoppingcart.domain.database

import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.Product
import br.com.dev.shoppingcart.domain.model.CartProduct

object Database {

    val allCarts = mutableListOf(
        Cart(1, "1"),
        Cart(2, "2")
    )

    val allProducts = mutableListOf(
        Product(1, "Shorts", 55.00, "", "Vestuário"),
        Product(2, "Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
        Product(3, "Camiseta", 80.0, "Camiseta Branca", "Vestuário")
    )

    val allProductsCart = mutableListOf(CartProduct(1, 1))
}
