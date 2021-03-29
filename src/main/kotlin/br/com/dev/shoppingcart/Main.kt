package br.com.dev.shoppingcart

import br.com.dev.shoppingcart.config.cartControllerModule
import br.com.dev.shoppingcart.config.cartRepositoryModule
import br.com.dev.shoppingcart.config.cartServiceModule
import br.com.dev.shoppingcart.contract.CartController
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.get
import io.javalin.apibuilder.ApiBuilder.path
import io.javalin.plugin.json.JavalinJackson.configure
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object Main : KoinComponent {

    private val cartController: CartController by inject()

    fun start() {
        startKoin {
            modules(listOf(cartControllerModule, cartServiceModule, cartRepositoryModule))
        }

        Javalin.create().apply {

            exception(Exception::class.java) { e, _ -> e.printStackTrace() }
            error(404) { ctx -> ctx.json("not found") }

            this.routes {
                path("cart/:user-id") {
                    get(cartController::getCart)

                    path("products") {
                        get(cartController::getAllProductsFromCart)
                    }
                }
            }

            val objectMapper = ObjectMapper()
            objectMapper.propertyNamingStrategy = PropertyNamingStrategies.SNAKE_CASE
            configure(objectMapper)

        }.start(8000)

    }

}

fun main() {
    Main.start()
}
