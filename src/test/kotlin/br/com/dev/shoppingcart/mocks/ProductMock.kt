package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.model.Product

object ProductMock {

    fun getThreeProducts() = listOf(
        Product("Camiseta", 100.00, "", "Vestuário"),
        Product("Shorts", 20.00, "", "Vestuário"),
        Product("Tênis", 159.00, "", "Vestuário")
    )
}