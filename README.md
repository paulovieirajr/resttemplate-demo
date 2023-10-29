# API with RestTemplate

This project consists of an API that consumes an external API (https://dummyjson.com/products) and provides three
endpoints
that allow:

1. Search all products.
2. Search for a product by ID.
3. Search for products filtered by name.

All project functionalities are covered by tests, both in the controllers and in the client layer that is
connects to external API.

## Endpoints

### Search All Products

This endpoint allows you to search for all available products.

- **URL:** `/api/products`
- **Method:** GET
- **Request Example:** `http://localhost:8080/api/products`

### Search Product by ID

This endpoint allows you to search for a specific product by ID.

- **URL:** `/api/products/{id}`
- **Method:** GET
- **Request Example:** `http://localhost:8080/api/products/1`

### Search Products by Name

This endpoint allows you to search for products filtered by name.

- **URL:** `/api/products/search`
- **Method:** GET
- **Query Parameters:** `q` (product name)
- **Request Example:** `http://localhost:8080/api/products/search?q=produto`

## Tests

All features of this project are tested to ensure they work correctly. The tests cover both
controllers and the client layer that communicates with the external API.

### Unit Tests

To run the unit tests, simply run the following command:

```
mvn test -Punit-tests
```

### Integration Tests

To run the integration tests, simply run the following command:

```
mvn verify -Pintegration-tests
```

## Documentation on Swagger

This project has documentation on Swagger, which provides detailed information about the endpoints and allows you to
test them
interactively.

To access the documentation on Swagger, simply open the following URL in your browser:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Make sure the application is running to access the documentation.

## How to Execute the Project

To run the project, follow these steps:

1. Clone the repository to your local environment.
2. Make sure you have Java and Maven installed.
3. Navigate to the project directory and run the following command:

```
mvn spring-boot:run
```

4. The application will be available at [http://localhost:8080](http://localhost:8080).

You can now use the endpoints and access documentation in Swagger to explore the project.
