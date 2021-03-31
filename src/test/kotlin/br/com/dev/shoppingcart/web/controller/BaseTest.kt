package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.config.GlobalObjectMapper
import br.com.dev.shoppingcart.domain.service.CartService
import br.com.dev.shoppingcart.domain.service.ProductService
import br.com.dev.shoppingcart.web.Router
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import io.mockk.impl.annotations.MockK
import io.restassured.RestAssured
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class BaseTest {

    private lateinit var app: Javalin

    private lateinit var cartController: CartController

    private lateinit var productController: ProductController

    @MockK
    lateinit var cartService: CartService

    @MockK
    lateinit var productService: ProductService

    private companion object {
        const val PORT = 8000
    }

    @BeforeEach
    fun setUp() {
        app = Javalin.create()
        JavalinJackson.configure(GlobalObjectMapper.getObjectMapper())

        cartController = CartController(cartService, productService)
        productController = ProductController(productService)

        val router = Router(cartController, productController)
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
