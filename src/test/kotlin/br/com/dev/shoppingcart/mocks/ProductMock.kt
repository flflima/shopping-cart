package br.com.dev.shoppingcart.mocks

import br.com.dev.shoppingcart.domain.model.Product

object ProductMock {

    fun getOneProductWithCamiseta() = Product(1, "Camiseta", 100.00, "", "Vestuário")
    fun getOneProductWithShorts() = Product(1, "Shorts", 20.00, "", "Vestuário")
    fun getOneProductWithTenis() = Product(1, "Tênis", 159.00, "", "Vestuário")

    fun getListWithOneProduct() = listOf(Product(1, "Camiseta", 100.00, "", "Vestuário"))

    fun getListWithThreeProducts() = listOf(
        getOneProductWithCamiseta(),
        getOneProductWithShorts(),
        getOneProductWithTenis()
    )
}