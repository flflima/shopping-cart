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
- [Swagger](https://swagger.io/)

## Building Application

```console
./gradlew clean build
```

## Testing Application

```console
./gradlew clean test
```

Minimum coverage is 80%, and report in _build/jacocoHtml/index.html_

## Running Sonarqube locally

```console
./gradlew clean test sonarqube \
  -Dsonar.projectKey=<<your-project-key-configured>> \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.login=<<the-login>>
```

## Running Application

```console
java -jar build/libs/shopping-cart-<version>.jar
```

## Application's Swagger

http://localhost:8000/swagger-ui.html

## Operations

Create a cart associated to a user

```curl
curl -X 'POST' \
  'http://localhost:8000/cart' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "user_id": "123"
}'
```
```curl
{
  "user_id": "123",
  "products": []
}
```

Get a cart associated to a user

```curl
curl -X 'GET' \
  'http://localhost:8000/cart/user/123' \
  -H 'accept: application/json'
```
```curl
{
  "user_id": "123",
  "products": []
}
```

Add a product to a cart 

```curl
curl -X 'POST' \
  'http://localhost:8000/cart/user/123/products' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 1,
  "quantity": 1
}'
```
```curl
{
  "user_id": "123",
  "products": [
    {
      "id": 1,
      "name": "Shorts",
      "price": 55,
      "description": "",
      "category": "Clothes",
      "quantity": 1
    }
  ]
}
```

Create a product

```curl
curl -X 'POST' \
  'http://localhost:8000/product' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Sneakers",
  "price": 199.99,
  "description": "A blue sneaker",
  "category": "Clothes"
}'
```
```curl
{
  "id": 4,
  "name": "Sneakers",
  "price": 199.99,
  "description": "A blue sneaker",
  "category": "Clothes"
}
```


Get a product by id

```curl
curl -X 'GET' \
  'http://localhost:8000/product/4' \
  -H 'accept: application/json'
```
```curl
{
  "id": 4,
  "name": "Sneakers",
  "price": 199.99,
  "description": "A blue sneaker",
  "category": "Clothes"
}
```
