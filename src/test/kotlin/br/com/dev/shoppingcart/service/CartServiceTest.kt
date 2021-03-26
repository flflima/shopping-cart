package br.com.dev.shoppingcart.service

import br.com.dev.shoppingcart.dao.CartRepository
import br.com.dev.shoppingcart.mocks.CartMock
import br.com.dev.shoppingcart.mocks.ProductMock
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CartServiceTest {

    private lateinit var sut: CartService

    @MockK
    lateinit var cartRepository: CartRepository

    @BeforeEach
    fun setUp() {
        this.sut = CartService(this.cartRepository)
    }

    @Test
    fun `given an existing user id must return all products related to it`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) }.returns(CartMock.getOneEmptyCart())
        every { cartRepository.getCartProductsByUserId(any()) }.returns(ProductMock.getThreeProducts())

        // act
        val allProducts = this.sut.getAllProductsFromCart("1")

        // assert
        assertThat(allProducts).isNotNull
    }

    @Test
    fun `given a non existing user id must return null`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) }.returns(null)
        every { cartRepository.getCartProductsByUserId(any()) }.returns(ProductMock.getThreeProducts())

        // act
        val allProducts = this.sut.getAllProductsFromCart("101")

        // assert
        assertThat(allProducts).isNull()
    }

    @Test
    fun `given an existing user id must return a cart`() {
        // arrange
        every { cartRepository.getCartByUserId(any()) }.returns(CartMock.getOneEmptyCart())

        // act
        val cart = this.sut.getCart("1")

        // assert
        assertThat(cart).isNotNull
    }
}