package br.com.dev.shoppingcart.domain.model

import br.com.dev.shoppingcart.web.dto.ProductDTO

data class Product(
    val name: String,
    val price: Double,
    val description: String,
    val category: String
)

fun Product.toProductDTO() = ProductDTO(this.name, this.price, this.description, this.category)
