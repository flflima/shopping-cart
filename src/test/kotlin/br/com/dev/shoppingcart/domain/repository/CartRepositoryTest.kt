package br.com.dev.shoppingcart.domain.repository

import br.com.dev.shoppingcart.domain.model.Cart
import br.com.dev.shoppingcart.domain.model.CartProduct
import br.com.dev.shoppingcart.domain.model.Product
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CartRepositoryTest {

    private lateinit var cut: CartRepository

    @MockK
    private lateinit var cartProducts: MutableList<CartProduct>

    @MockK
    private lateinit var products: MutableList<Product>

    @Test
    fun `given an user id if it exists must return a list of carts`() {
        // arrange
        this.cut = CartRepository(mutableListOf(Cart(1, "1")), cartProducts, products)

        // act
        val cart = this.cut.getCartByUserId("1")

        // assert
        assertThat(cart).isNotEmpty
        assertThat(cart).size().isEqualTo(1)
    }

    @Test
    fun `given an user id if it does not exist must return an empty list`() {
        // arrange
        this.cut = CartRepository(mutableListOf(Cart(1, "1")), cartProducts, products)

        // act
        val cart = this.cut.getCartByUserId("10")

        // assert
        assertThat(cart).isEmpty()
    }

    @Test
    fun `given an user id if it does not has a cart must create a new one and return in the list`() {
        // arrange
        val cartProducts = mutableListOf<CartProduct>()
        this.cut = CartRepository(mutableListOf(), cartProducts, products)

        // act
        val carts = this.cut.createCart("10")

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().userId).isEqualTo("10")
        assertThat(cartProducts).isEmpty()
    }

    @Test
    fun `given an user id and a product id if it does not has a cart must create a new one with products and return in the list`() {
        // arrange
        val cartProducts = mutableListOf<CartProduct>()
        this.cut = CartRepository(mutableListOf(), cartProducts, products)

        // act
        val carts = this.cut.createCart("1", 1)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().userId).isEqualTo("1")
        assertThat(cartProducts).size().isEqualTo(1)
    }

    @Test
    fun `given an user id that has a cart must not create a new one and return in the list`() {
        // arrange
        val cartProducts = mutableListOf<CartProduct>()
        this.cut = CartRepository(mutableListOf(Cart(1, "1")), cartProducts, products)

        // act
        val carts = this.cut.createCart("1", 1)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().userId).isEqualTo("1")
        assertThat(cartProducts).isEmpty()
    }

    @Test
    fun `given a valid cart id must return a list of products`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf(CartProduct(1, 1))
        val products = mutableListOf(Product(1, "Tenis", 100.0, "", ""))
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val productsByCartId = this.cut.getProductsByCartId(1)

        // assert
        assertThat(productsByCartId).isNotEmpty
        assertThat(productsByCartId.first().id).isEqualTo(1)
    }

    @Test
    fun `given a valid cart id but with no products in the cart must return an empty list of products`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf(CartProduct(1, 1))
        val products = mutableListOf(Product(10, "Tenis", 100.0, "", ""))
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val productsByCartId = this.cut.getProductsByCartId(1)

        // assert
        assertThat(productsByCartId).isEmpty()
    }

    @Test
    fun `given an invalid cart id must return an empty list of products`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf(CartProduct(1, 1))
        val products = mutableListOf(Product(1, "Tenis", 100.0, "", ""))
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val productsByCartId = this.cut.getProductsByCartId(100)

        // assert
        assertThat(productsByCartId).isEmpty()
    }

    @Test
    fun `when adding a product to a cart if the cart does not exist must return a new cart`() {
        // arrange
        val products = mutableListOf(Product(1, "Tenis", 100.0, "", ""))
        val cartProducts = mutableListOf<CartProduct>()
        this.cut = CartRepository(mutableListOf(), cartProducts, products)

        // act
        val carts = this.cut.addProduct("1", 1, 10)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().cartId).isEqualTo(1)

        assertThat(cartProducts).size().isEqualTo(1)
        assertThat(cartProducts.first().productID).isEqualTo(1)
        assertThat(cartProducts.first().quantity).isEqualTo(10)
    }

    @Test
    fun `when adding a product to a cart if the cart already exists and is empty must return a cart with a new product`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf<CartProduct>()
        val products = mutableListOf(
            Product(1, "Tenis", 100.0, "", ""),
            Product(2, "Tenis em Promocao", 99.0, "", "")
        )
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val carts = this.cut.addProduct("1", 2, 5)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().cartId).isEqualTo(1)

        assertThat(cartProducts).size().isEqualTo(1)
    }

    @Test
    fun `when adding a product to a cart if the cart already exists and has a different product must return a cart with a new product`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf(CartProduct(1, 2, 10))
        val products = mutableListOf(
            Product(1, "Tenis", 100.0, "", ""),
            Product(2, "Tenis em Promocao", 99.0, "", "")
        )
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val carts = this.cut.addProduct("1", 1, 5)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().cartId).isEqualTo(1)

        assertThat(cartProducts).size().isEqualTo(2)
    }

    @Test
    fun `when adding a product to a cart if the cart already exists and has the same product that is being added must return the quantity updated`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf(CartProduct(1, 1, 2))
        val products = mutableListOf(
            Product(1, "Tenis", 100.0, "", ""),
            Product(2, "Tenis em Promocao", 99.0, "", "")
        )
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val carts = this.cut.addProduct("1", 1, 5)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().cartId).isEqualTo(1)

        assertThat(cartProducts).size().isEqualTo(1)
        assertThat(cartProducts.first().quantity).isEqualTo(7)
    }

    @Test
    fun `when adding a product to a cart if the cart already exists but is empty then must return the quantity informed`() {
        // arrange
        val cart = mutableListOf(Cart(1, "1"))
        val cartProducts = mutableListOf<CartProduct>()
        val products = mutableListOf(
            Product(1, "Tenis", 100.0, "", ""),
            Product(2, "Tenis em Promocao", 99.0, "", "")
        )
        this.cut = CartRepository(cart, cartProducts, products)

        // act
        val carts = this.cut.addProduct("1", 1, 5)

        // assert
        assertThat(carts).isNotEmpty
        assertThat(carts).size().isEqualTo(1)
        assertThat(carts.first().cartId).isEqualTo(1)

        assertThat(cartProducts).size().isEqualTo(1)
        assertThat(cartProducts.first().quantity).isEqualTo(5)
    }

    @Test
    fun `given an existing cart id and product id must return a cart with a product`() {
        // arrange
        val cartProducts = mutableListOf(CartProduct(1, 1, 2))

        this.cut = CartRepository(mutableListOf(), cartProducts, mutableListOf())

        // act
        val cartProduct = this.cut.getQuantityFromCartByIdAndProductId(1, 1)

        // assert
        assertThat(cartProduct).isNotNull
        assertThat(cartProduct!!.cartId).isEqualTo(1)
        assertThat(cartProduct.productID).isEqualTo(1)
    }

    @Test
    fun `given a non existing cart id must return a cart with a product`() {
        // arrange
        val cartProducts = mutableListOf(CartProduct(1, 1, 2))

        this.cut = CartRepository(mutableListOf(), cartProducts, mutableListOf())

        // act
        val cartProduct = this.cut.getQuantityFromCartByIdAndProductId(10, 1)

        // assert
        assertThat(cartProduct).isNull()
    }

    @Test
    fun `given a non existing product id must return a cart with a product`() {
        // arrange
        val cartProducts = mutableListOf(CartProduct(1, 1, 2))

        this.cut = CartRepository(mutableListOf(), cartProducts, mutableListOf())

        // act
        val cartProduct = this.cut.getQuantityFromCartByIdAndProductId(1, 10)

        // assert
        assertThat(cartProduct).isNull()
    }
}