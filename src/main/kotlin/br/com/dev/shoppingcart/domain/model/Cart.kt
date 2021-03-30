package br.com.dev.shoppingcart.domain.model

data class Cart(val userId: String, val products: List<Product>)
