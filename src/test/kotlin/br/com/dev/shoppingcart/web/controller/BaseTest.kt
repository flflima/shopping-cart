package br.com.dev.shoppingcart.web.controller

import br.com.dev.shoppingcart.config.GlobalObjectMapper
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import io.restassured.RestAssured

abstract class BaseTest {

    protected lateinit var server: Javalin

    private companion object {
        const val PORT = 8000
    }

    open fun setUp() {
        this.server = Javalin.create().apply {
            JavalinJackson.configure(GlobalObjectMapper.getObjectMapper())
        }.start(PORT)

        RestAssured.port = PORT
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
    }

    open fun `tear down`() {
        server.stop()
    }
}
