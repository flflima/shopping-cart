package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.repository.ProductRepository
import br.com.dev.shoppingcart.web.dto.ProductDTO
import io.javalin.http.NotFoundResponse

class ProductService(private val productRepository: ProductRepository) {

    fun getProductById(productId: Long) = productRepository.findProductById(productId).let {
        it ?: throw NotFoundResponse("Product with id $productId not found!")
    }

    fun createProduct(productDTO: ProductDTO) = productRepository.saveProduct(productDTO)
}