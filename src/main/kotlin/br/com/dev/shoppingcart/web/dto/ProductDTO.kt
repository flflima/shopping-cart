package br.com.dev.shoppingcart.web.dto

data class ProductDTO(
    val name: String,
    val price: Double,
    val description: String = "",
    val category: String,
    val quantity: Int
)
