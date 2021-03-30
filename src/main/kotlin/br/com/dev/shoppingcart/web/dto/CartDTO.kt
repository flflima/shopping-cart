package br.com.dev.shoppingcart.web.dto

data class CartDTO(val userId: String, val products: List<ProductDTO>)
