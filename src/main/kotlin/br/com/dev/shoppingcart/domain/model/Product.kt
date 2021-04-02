package br.com.dev.shoppingcart.domain.model

import br.com.dev.shoppingcart.web.dto.ProductDTO

data class Product(
    val id: Long,
    val name: String,
    val price: Double,
    val description: String,
    val category: String
)

fun Product.toProductDTO(quantity: Int? = null) =
    ProductDTO(
        id = this.id,
        name = this.name,
        price = this.price,
        description = this.description,
        category = this.category,
        quantity = quantity
    )
