# markovski-solutions

# User Management Application

The User Management Application is an application built with Java, Spring Boot, and PostgreSQL to manage user information. It provides the following functionalities:

## 1. Find User by ID

Endpoint: GET /api/users/{id}

This functionality allows you to retrieve a user by their ID. The user ID must be provided as a path parameter. If the ID is valid and corresponds to an existing user, the API will return the user information in the response body. If the ID is invalid or the user does not exist, an appropriate error response will be returned.

## 2. Get All Users

Endpoint: GET /api/users

This functionality retrieves all the users stored in the database. It returns a list of user information in the response body.

## 3. Add User

Endpoint: POST /api/users/add

This functionality allows you to add a new user to the database. The user information should be provided in the request body as JSON. After successfully adding the user, the API will return the created user information in the response body along with an HTTP Created status code.

## 4. Update User

Endpoint: PUT /api/users/{id}

This functionality allows you to update an existing user in the database. The user ID must be provided as a path parameter, and the updated user information should be provided in the request body as JSON. If the ID is valid and corresponds to an existing user, the API will update the user information and return the updated user information in the response body. If the ID is invalid or the user does not exist, an appropriate error response will be returned.

## 5. Delete User

Endpoint: DELETE /api/users/{id}

This functionality allows you to delete a user from the database. The user ID must be provided as a path parameter. If the ID is valid and corresponds to an existing user, the API will delete the user and return the deleted user information in the response body. If the ID is invalid or the user does not exist, an appropriate error response will be returned.

## Error Handling

The application handles various error scenarios and returns appropriate HTTP error responses. If an invalid user ID is provided or a user is not found, the API will return an HTTP Not Found response. If an invalid request is made or there is a validation error, the API will return an HTTP Bad Request response.

---
## How to Run the Project

To run the User Management Application locally, follow these steps:

1. Clone the repository to your local machine.
2. Make sure you have Java 17 and PostgreSQL installed.
3. Create a new PostgreSQL database named `usermanagement`.
4. Open the project in your favorite IDE.
5. Configure the database connection properties in the `application.properties` file.
6. Build the project using the provided build tools (e.g., Maven or Gradle).
7. Run the application.
8. The User Management Application will be accessible at `http://localhost:8080`.

Make sure to customize the database connection properties according to your local setup. You may need to update the database URL, username, and password in the `application.properties` file.
This README provides an overview of the functionalities available in the User Management Application. For detailed information about the API endpoints, request/response formats, and error handling, please refer to the API documentation or contact the developers.
