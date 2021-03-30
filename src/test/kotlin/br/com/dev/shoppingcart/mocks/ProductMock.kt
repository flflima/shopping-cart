package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.domain.model.Product

object ProductMock {

    fun getOneProduct() = Product(1, "Camiseta", 100.00, "", "Vestuário")

    fun getListWithOneProduct() = listOf(Product(1, "Camiseta", 100.00, "", "Vestuário"))

    fun getListWithThreeProducts() = listOf(
        Product(1, "Camiseta", 100.00, "", "Vestuário"),
        Product(2, "Shorts", 20.00, "", "Vestuário"),
        Product(3, "Tênis", 159.00, "", "Vestuário")
    )
}