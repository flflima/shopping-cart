package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.database.Database.allProducts
import br.com.dev.shoppingcart.domain.model.Product
import br.com.dev.shoppingcart.web.dto.ProductDTO

class ProductRepository {

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
        allProducts.add(product)
        return product
    }
}