package br.com.dev.shoppingcart

import br.com.dev.shoppingcart.config.KoinConfig.listOfModules
import br.com.dev.shoppingcart.web.Router
import com.fasterxml.jackson.databind.ObjectMapper
import io.javalin.Javalin
import io.javalin.plugin.json.JavalinJackson
import org.eclipse.jetty.server.Server
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object App : KoinComponent {

    private val objectMapper: ObjectMapper by inject()

    private val router: Router by inject()

    private lateinit var server: Javalin

    fun start() {
        startKoin {
            printLogger()
            modules(listOfModules)
        }

        JavalinJackson.configure(objectMapper)
        server = Javalin.create {
            it.addStaticFiles("/swagger")
            it.addSinglePageRoot("","/swagger/swagger-ui.html")
            it.server {
                Server(8000)
            }
        }

        router.configure(server)
        server.start()
    }

    fun stop() {
        server.stop()
    }

}

fun main() {
    App.start()
}
