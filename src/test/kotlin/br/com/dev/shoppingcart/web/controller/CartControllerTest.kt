package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.mocks.CartMock
import br.com.dev.shoppingcart.mocks.ProductMock
import br.com.dev.shoppingcart.web.dto.CartDTO
import io.javalin.http.ConflictResponse
import io.javalin.http.NotFoundResponse
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CartControllerTest : BaseTest() {

    @Test
    fun `given a request for an existing cart must return all products`() {
        every { cartService.getCart(any()) } returns CartMock.getOneCartWithThreeProducts()

        given()
            .`when`()
            .get("cart/1")
            .then()
            .assertThat()
            .statusCode(200)
            .body("user_id", equalTo("1"))
            .body("products[0].name", equalTo("Camiseta"))
            .body("products[0].price", equalTo(100.0f))
            .body("products[0].description", equalTo(""))
            .body("products[0].category", equalTo("Vestuário"))
            .body("products[1].name", equalTo("Shorts"))
            .body("products[1].price", equalTo(20.0f))
            .body("products[1].description", equalTo(""))
            .body("products[1].category", equalTo("Vestuário"))
            .body("products[2].name", equalTo("Tênis"))
            .body("products[2].price", equalTo(159.0f))
            .body("products[2].description", equalTo(""))
            .body("products[2].category", equalTo("Vestuário"))
    }

    @Test
    fun `given a request for a non existing cart must return a not found status code`() {
        every { cartService.getCart(any()) } throws NotFoundResponse()

        given()
            .`when`()
            .get("cart/1")
            .then()
            .assertThat()
            .statusCode(404)
            .body(equalTo("Not found"))
    }

    @Test
    fun `given a request for all products in a cart must return a list of products`() {
        every { cartService.getAllProductsFromCart(any()) } returns ProductMock.getOneProduct()

        given()
            .`when`()
            .get("cart/1/products")
            .then()
            .assertThat()
            .statusCode(200)
            .body("size()", equalTo(1))
            .body("[0].name", equalTo("Camiseta"))
            .body("[0].price", equalTo(100.0f))
            .body("[0].description", equalTo(""))
            .body("[0].category", equalTo("Vestuário"))
    }

    @Test
    fun `given a request for all products to a non existing cart must return a not found status code`() {
        every { cartService.getAllProductsFromCart(any()) } throws NotFoundResponse()

        given()
            .`when`()
            .get("cart/1/products")
            .then()
            .assertThat()
            .statusCode(404)
            .body(equalTo("Not found"))
    }

    @Test
    fun `given a request to create a cart for an user must return a new cart with empty products list`() {
        every { cartService.createCartByUserId(any()) } returns CartDTO("1")

        val body = """
                {
                    "user_id": "1"
                }
            """.trimIndent()

        given()
            .body(
                body
            )
            .header("Content-Type", "application/json")
            .`when`()
            .post("cart")
            .then()
            .assertThat()
            .body("user_id", equalTo("1"))
            .body("products.size()", equalTo(0))
            .statusCode(201)
    }

    @Test
    fun `given a request to create a cart that already exists must return a conflict status code`() {
        every { cartService.createCartByUserId(any()) } throws ConflictResponse()

        val body = """
                {
                    "user_id": "1"
                }
            """.trimIndent()

        given()
            .body(
                body
            )
            .header("Content-Type", "application/json")
            .`when`()
            .post("cart")
            .then()
            .assertThat()
            .statusCode(409)
            .body(equalTo("Conflict"))
    }

}
