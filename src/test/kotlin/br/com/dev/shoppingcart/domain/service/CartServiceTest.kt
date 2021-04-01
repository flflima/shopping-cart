package br.com.dev.shoppingcart.domain.service

import br.com.dev.shoppingcart.domain.repository.CartRepository
import br.com.dev.shoppingcart.domain.repository.ProductRepository
import br.com.dev.shoppingcart.mocks.CartMock
import br.com.dev.shoppingcart.mocks.ProductMock
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.ConflictResponse
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
internal class CartServiceTest {

    private lateinit var sut: CartService

    @MockK
    lateinit var cartRepository: CartRepository

    @MockK
    lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        this.sut = CartService(this.cartRepository, productRepository)
    }

    @Test
    fun `given an user id must return a cart`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) } returns CartMock.getOneEmptyCart()
        every { cartRepository.getProductsByCartId(any()) } returns ProductMock.getListWithOneProduct()

        // act
        val cart = this.sut.getCart("1")

        // assert
        assertThat(cart).isNotNull
    }

    @Test
    fun `given a non existing cart when requesting a cart must throw a NotFoundResponse exception`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) } returns emptyList()

        // act
        val exception = assertThrows<NotFoundResponse> {
            this.sut.getCart("1")
        }

        // assert
        assertThat(exception).isNotNull
        assertThat(exception).hasMessage("Cart not found!")
    }

    @Test
    fun `given a valid cart dto must create a cart`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) } returns emptyList()
        every { cartRepository.createCart(any()) } returns CartMock.getOneEmptyCart()

        val userId = "1"

        // act
        val cartDTO: CartDTO = this.sut.createCartByUserId(userId)

        // assert
        assertThat(cartDTO).isNotNull
        assertThat(cartDTO.products).isEmpty()
    }

    @Test
    fun `given an existing cart when trying to create a new cart with the same user id must throw a ConflictResponse exception`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) } returns CartMock.getOneEmptyCart()

        // act
        val exception = assertThrows<ConflictResponse> {
            this.sut.createCartByUserId("1")
        }

        // assert
        assertThat(exception).isNotNull
        assertThat(exception).hasMessage("Cart already exists!")
    }

    @Test
    fun `given a valid product id must return a cart with a new product`() {
        // arrange
        every { productRepository.findProductById(any()) } returns ProductMock.getOneProductWithTenis()
        every { cartRepository.addProduct(any(), any(), any()) } returns CartMock.getOneEmptyCartProduct()

        // act
        val cart = this.sut.addProduct("1", ProductMock.getOneProductWithTenis().id, 10)

        // assert
        assertThat(cart).isNotNull
        assertThat(cart.userId).isEqualTo("1")
        assertThat(cart.products).size().isEqualTo(1)
    }

    @Test
    fun `given a non existing product id must throw an exception`() {
        // arrange
        every { productRepository.findProductById(any()) } returns null

        // act
        val exception = assertThrows<NotFoundResponse> { this.sut.addProduct("1", 1, 10) }

        // assert
        assertThat(exception).isNotNull
        assertThat(exception).hasMessage("Product not found!")
    }
}
