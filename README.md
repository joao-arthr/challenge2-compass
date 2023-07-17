# challenge2-compass
RESTful API using the Spring Boot framework in Java for Compass UOL challenge 2

## 🚀 Starting


These instructions will allow you to get a copy of the project up and running on your local machine for development and testing purposes.

## 📋  Prerequisites
```
Java 17
IDE (ex: IntelliJ, Eclipse)
API client (ex: Postman, Insomnia)
```

## 🔧 Instalation

1. Clone the project:

```
git clone https://github.com/joao-arthr/challenge2-compass.git
```


## 🏍 Running
1. Open the project on your IDE and run it!

2. Open your API client and consume the API from the URL.

```
http://localhost:8080/
```

## 🚩 API Endpoints

The following endpoints are available in this API:

### 1. GET /products

Retrieve a list of all products.

### 2. GET /products/{id}

Retrieve details of a specific products.

- Parameters:
  - `id` (required): The ID of the products.

### 3. POST /products

Create a new products.

- Request Body:
  - `name` (required): The name of the product.
  - `price` (required): The price of the product.
  - `quantity` (required): The quantity of the product.

### 4. PUT /products/{id}

Update details of a specific product.

- Parameters:
  - `id` (required): The ID of the product.
- Request Body:
  - `name` (required): The name of the product.
  - `price` (required): The price of the product.
  - `quantity` (required): The quantity of the product.

### 5. DELETE /products/{id}

Delete a specific product.

- Parameters:
  - `id` (required): The ID of the products.

## ✒️ Author

* **Artur Siqueira** - [artursiqueira](https://github.com/artursiqueira)
* **João Arthur** - [joao-arthr](https://github.com/joao-arthr)
* **Letícia Imasato** - [lesyimasato](https://github.com/lesyimasato)
* **Rhauan Masulim** - [RhauanMasulim](https://github.com/RhauanMasulim)
