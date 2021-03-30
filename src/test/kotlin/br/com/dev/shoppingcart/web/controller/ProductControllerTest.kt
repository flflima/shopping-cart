package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.mocks.ProductMock
import io.javalin.http.NotFoundResponse
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ProductControllerTest : BaseTest() {

    @Test
    fun `given a request for an existing product must return it`() {
        every { productService.getProductById(any()) } returns ProductMock.getOneProduct()

        RestAssured.given()
            .`when`()
            .get("product/1")
            .then()
            .assertThat()
            .statusCode(200)
            .body("name", equalTo("Camiseta"))
            .body("price", equalTo(100.0f))
            .body("description", equalTo(""))
            .body("category", equalTo("Vestuário"))
    }

    @Test
    fun `given a request for an non existing product must return a not found status code`() {
        every { productService.getProductById(any()) } throws NotFoundResponse()

        RestAssured.given()
            .`when`()
            .get("product/1")
            .then()
            .assertThat()
            .statusCode(404)
            .body(equalTo("Not found"))
    }
}