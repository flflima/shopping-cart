package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.repository.ProductRepository
import br.com.dev.shoppingcart.mocks.ProductMock
import io.javalin.http.NotFoundResponse
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ProductServiceTest {

    private lateinit var sut: ProductService

    @MockK
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        this.sut = ProductService(productRepository)
    }

    @Test
    fun `given a request must return a product by id`() {
        // arrange
        every { productRepository.findProductById(any()) } returns ProductMock.getOneProduct()

        // act
        val product = this.sut.getProductById(1)

        // assert
        assertThat(product).isNotNull
        assertThat(product.id).isEqualTo(1)
        assertThat(product.name).isEqualTo("Camiseta")
        assertThat(product.price).isEqualTo(100.0)
        assertThat(product.description).isEmpty()
        assertThat(product.category).isEqualTo("Vestuário")
    }

    @Test
    fun `given a request must thrown an exception when product does not exists`() {
        // arrange
        every { productRepository.findProductById(any()) } returns null

        // act
        val exception = assertThrows<NotFoundResponse> { this.sut.getProductById(1) }

        // assert
        assertThat(exception).isNotNull
        assertThat(exception).hasMessage("Product with id 1 not found!")
    }
}
