package br.com.dev.shoppingcart.contract

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import io.mockk.junit5.MockKExtension
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.hasItems
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class CartControllerContractTest {

    lateinit var wiremockServerItem: WireMockServer

    companion object {
        const val PORT = 8000
    }

    @BeforeEach
    fun setUp() {
        RestAssured.port = PORT
        wiremockServerItem = WireMockServer(PORT)
        wiremockServerItem.start()
    }

    @AfterEach
    fun tearDown() {
        wiremockServerItem.stop()
    }

    @Test
    fun `given an existing user id must return a json containing a list of products and status code 200`() {
        val bodyJson = """
            {
                "name": "Camiseta",
                "price": 100.00,
                "description": "",
                "category": "Vestuário"
            }
        """.trimIndent()
        wiremockServerItem.stubFor(
            WireMock.get(urlEqualTo("/cart/10/products"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(bodyJson)
                        .withHeader("content-type", "application/json")))

        given()
            .`when`()
            .get("cart/10/products")
            .then()
            .assertThat()
            .statusCode(200)
            .body("name", equalTo("Camiseta"))
            .body("price", equalTo(100.0f))
            .body("description", equalTo(""))
            .body("category", equalTo("Vestuário"))
    }

    @Test
    fun `given a non existing user id must not return return a list of products and status code 404`() {
        wiremockServerItem.stubFor(
            WireMock.get(urlEqualTo("/cart/10/products"))
                .willReturn(
                    aResponse()
                        .withStatus(404)))

        given()
            .`when`()
            .get("cart/10/products")
            .then()
            .assertThat()
            .statusCode(404)
    }

    @Test
    fun `given an existing user id must return a json containing a cart and status code 200`() {
        val bodyJson = """
            {
                "user_id": "10",
                "products": [
                    {
                        "name": "Camiseta",
                        "price": 100.00,
                        "description": "",
                        "category": "Vestuário"
                    },
                    {
                        "name": "Tênis",
                        "price": 159.90,
                        "description": "Tênis Azul número 36",
                        "category": "Vestuário"
                    }
                 ]
            }
        """.trimIndent()
        wiremockServerItem.stubFor(
            WireMock.get(urlEqualTo("/cart/10"))
                .willReturn(
                    aResponse()
                        .withStatus(200)
                        .withBody(bodyJson)
                        .withHeader("content-type", "application/json")))

        given()
            .`when`()
            .get("cart/10")
            .then()
            .assertThat()
            .statusCode(200)
            .body("user_id", equalTo("10"))
            .body("products.name", hasItems("Camiseta", "Tênis"))
            .body("products.price", hasItems(100.0f, 159.9f))
            .body("products.description", hasItems("", "Tênis Azul número 36"))
            .body("products.category", hasItems("Vestuário", "Vestuário"))
    }

    @Test
    fun `given a non existing user id must not return a cart and status code 404`() {
        wiremockServerItem.stubFor(
            WireMock.get(urlEqualTo("/cart/10"))
                .willReturn(
                    aResponse()
                        .withStatus(404)))

        given()
            .`when`()
            .get("cart/10")
            .then()
            .assertThat()
            .statusCode(404)
    }
}