# Shopping Cart

A simple REST application with Kotlin

## Stack

- [Kotlin](https://developer.android.com/kotlin)
- [Javalin](https://javalin.io/)
- [Gradle](https://gradle.org/)
- [Mockk](https://mockk.io/)
- [AssertJ](https://joel-costigliola.github.io/assertj/index.html)

## Building Application

```console
./gradlew clean build
```

## Running Application

```console
java -jar build/libs/shopping-cart-<version>.jar
```

## Operations

Get all products added to a chart for the same user

```curl
curl 'localhost:8000/cart/{user-id}/products' 
```
