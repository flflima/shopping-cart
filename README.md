# Shopping Cart

A simple REST application with Kotlin

## Stack

- [Kotlin](https://developer.android.com/kotlin)
- [Javalin](https://javalin.io/)
- [Gradle](https://gradle.org/)
- [Mockk](https://mockk.io/)
- [AssertJ](https://joel-costigliola.github.io/assertj/index.html)
- [Koin](https://insert-koin.io/)
- [JaCoCo](https://www.jacoco.org/jacoco/trunk/doc/)

## Building Application

```console
./gradlew clean build
```

## Running Application

```console
java -jar build/libs/shopping-cart-<version>.jar
```

## Operations

Create a cart associated to a user

```curl
curl --request POST 'localhost:8000/cart' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user_id": "123"
}'
```

Get a cart associated to a user

```curl
curl 'localhost:8000/cart/123' 
```

Get all products added to a chart for the same user

```curl
curl 'localhost:8000/cart/123/products' 
```
