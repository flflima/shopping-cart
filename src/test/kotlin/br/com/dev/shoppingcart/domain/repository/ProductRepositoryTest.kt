package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.Product
import br.com.dev.shoppingcart.mocks.ProductMock
import br.com.dev.shoppingcart.web.dto.ProductDTO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProductRepositoryTest {

    private lateinit var cut: ProductRepository

    @Test
    fun `given a product id when a product with that id exists must return it`() {
        // arrange
        this.cut = ProductRepository(ProductMock.getListWithThreeProducts())

        // act
        val product = this.cut.findProductById(1)

        // assert
        assertThat(product).isNotNull
        assertThat(product?.id).isEqualTo(1)
    }

    @Test
    fun `given a product id when a product with that id does not exists must return null`() {
        // arrange
        this.cut = ProductRepository(ProductMock.getListWithThreeProducts())

        // act
        val product = this.cut.findProductById(100)

        // assert
        assertThat(product).isNull()
    }

    @Test
    fun `given an empty list of products when adding a new one must be of size one and id 1`() {
        // arrange
        val products = mutableListOf<Product>()
        this.cut = ProductRepository(products)

        // act
        val product =
            this.cut.saveProduct(ProductDTO(name = "Test", price = 100.0, description = "", category = "test"))

        // assert
        assertThat(product).isNotNull
        assertThat(product.id).isEqualTo(1)

        assertThat(products).size().isEqualTo(1)
    }

    @Test
    fun `given a list of products with one element when adding a new one must be of size two`() {
        // arrange
        val products = ProductMock.getListWithOneProduct()
        
        this.cut = ProductRepository(products)

        // act
        val product =
            this.cut.saveProduct(ProductDTO(name = "Test", price = 100.0, description = "", category = "test"))

        // assert
        assertThat(product).isNotNull
        assertThat(products).size().isEqualTo(2)
    }
}