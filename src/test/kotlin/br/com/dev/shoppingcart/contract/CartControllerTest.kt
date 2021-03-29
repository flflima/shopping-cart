package br.com.dev.shoppingcart.contract

import br.com.dev.shoppingcart.Main
import br.com.dev.shoppingcart.mocks.CartMock
import br.com.dev.shoppingcart.mocks.ProductMock
import br.com.dev.shoppingcart.service.CartService
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.RestAssured.port
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CartControllerTest {

    private lateinit var server: Javalin

    private lateinit var sut: CartController

    @MockK
    private lateinit var cartService: CartService

    companion object {
        const val PORT = 8000
    }

    @BeforeEach
    fun setUp() {
        port = PORT
        this.sut = CartController(cartService)
        this.server = Javalin.create()
            .apply {
                this.routes {
                    path("cart/:user-id") {
                        get(sut::getCart)
                    }
                    path("cart/:user-id/products") {
                        get(sut::getAllProductsFromCart)
                    }
                }
            }
            .start(PORT)
//        Main.start()
    }

    @AfterEach
    fun `tear down`() {
        server.stop()
    }

    @Test
    fun `given a request fo a cart must return all data into a cart`() {
        every { cartService.getCart(any()) } returns CartMock.getOneCartWithThreeProducts()

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        given()
            .`when`()
            .get("cart/1")
            .then()
            .assertThat()
            .statusCode(200)
            .body("products.[0].name", equalTo("Camiseta"))
            .body("user_id", equalTo(1))
//            .body("products.[0].price", equalTo(100.0f))
//            .body("products.[0].description", equalTo(""))
//            .body("products.[0].category", equalTo("Vestuário"))
    }

//    @Test
//    fun `given a request for all products in a cart must return a list of products`() {
//        every { cartService.getAllProductsFromCart(any()) } returns ProductMock.getOneProduct()
//
//        given()
//            .`when`()
//            .get("cart/1/products")
//            .then()
//            .assertThat()
//            .statusCode(200)
//            .body("size()", equalTo(1))
//            .body("[0].name", equalTo("Camiseta"))
//            .body("[0].price", equalTo(100.0f))
//            .body("[0].description", equalTo(""))
//            .body("[0].category", equalTo("Vestuário"))
//    }
}
