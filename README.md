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
curl --request POST 'localhost:8000/carts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "user_id": "123"
}'

{"user_id":"123","products":[]}
```

Get a cart associated to a user

```curl
curl 'localhost:8000/cart/123' 

{"user_id":"123","products":[]}
```

Add a product to a cart 

```curl
curl --request POST 'localhost:8000/cart/123/products' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": 4,
    "quantity": 1
}'

{"id":"4","products":[{"name":"Sneakers","price":99.99,"description":"","category":"Clothing","quantity":1}]}
```

Create a product

```curl
curl --request POST 'localhost:8000/products' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Sneakers",
    "price": 99.99,
    "description": "",
    "category": "Clothing"
}'

{"id":4,"name":"Sneakers","price":99.99,"description":"","category":"Clothing"}
```

Get a product by id

```curl
curl 'localhost:8000/product/4' 

{"name":"Sneakers","price":99.99,"description":"","category":"Clothing"}
```
