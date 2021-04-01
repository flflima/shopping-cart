package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.Product
import br.com.dev.shoppingcart.web.dto.ProductDTO

class ProductRepository(private val products: MutableList<Product>) {

    fun findProductById(productId: Long): Product? = products.firstOrNull {
        it.id == productId
    }

    fun saveProduct(productDTO: ProductDTO): Product {
        val product = Product(
            (products.size + 1).toLong(),
            productDTO.name,
            productDTO.price,
            productDTO.description,
            productDTO.category
        )
        products.add(product)
        return product
    }
}