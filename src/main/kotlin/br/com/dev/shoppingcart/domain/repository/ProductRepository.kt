package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.Product
import br.com.dev.shoppingcart.web.dto.ProductDTO
import kotlin.random.Random

class ProductRepository {

    private val allProducts = mutableSetOf(
        Product(1, "Shorts", 55.00, "", "Vestuário"),
        Product(2, "Xadrez", 35.0, "Jogo de Xadrez", "Jogos de Tabuleiro"),
        Product(3, "Camiseta", 80.0, "Camiseta Branca", "Vestuário")
    )

    fun findProductById(productId: Long) = allProducts.firstOrNull {
        it.id == productId
    }

    fun saveProduct(productDTO: ProductDTO): Product {
        val product = Product(
            (allProducts.size + 1).toLong(),
            productDTO.name,
            productDTO.price,
            productDTO.description,
            productDTO.category
        )
        this.allProducts.add(product)
        return product
    }
}