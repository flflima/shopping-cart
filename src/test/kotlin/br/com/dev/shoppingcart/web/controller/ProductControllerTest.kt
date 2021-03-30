package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.mocks.ProductMock
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.restassured.RestAssured
import org.hamcrest.CoreMatchers
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
            .body("name", CoreMatchers.equalTo("Camiseta"))
            .body("price", CoreMatchers.equalTo(100.0f))
            .body("description", CoreMatchers.equalTo(""))
            .body("category", CoreMatchers.equalTo("Vestuário"))
    }
}