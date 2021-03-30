package br.com.dev.shoppingcart.web.dto

data class ProductDTO(
    val id: Long? = null,
    val name: String,
    val price: Double,
    val description: String,
    val category: String
)
