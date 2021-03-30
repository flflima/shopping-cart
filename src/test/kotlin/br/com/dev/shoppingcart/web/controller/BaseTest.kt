package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.config.GlobalObjectMapper
import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.web.Router
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import io.mockk.impl.annotations.MockK
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class BaseTest {

    private lateinit var app: Javalin

    private lateinit var sut: CartController

    @MockK
    lateinit var cartService: CartService

    private companion object {
        const val PORT = 8000
    }

    @BeforeEach
    fun setUp() {
        app = Javalin.create()
        JavalinJackson.configure(GlobalObjectMapper.getObjectMapper())

        sut = CartController(cartService)

        val router = Router(sut)
        router.configure(app)

        RestAssured.port = PORT
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()

        app.start(PORT)
    }

    @AfterEach
    fun `tear down`() {
        app.stop()
    }
}
