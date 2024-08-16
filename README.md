

# Library Management System API

## Overview

This API provides endpoints for managing books, patrons, and borrowing records in a library management system. It supports operations such as adding, updating, deleting, and retrieving books and patrons, as well as creating and returning borrowing records.

## API Endpoints

### 1. Book Management

#### Get All Books
- **Endpoint**: `/api/books`
- **Method**: `GET`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "title": "string",
      "author": "string",
      "isbn": "string",
      "available": true
    }
  ]
  ```

#### Get Book by ID
- **Endpoint**: `/api/books/{id}`
- **Method**: `GET`
- **Path Parameters**:
  - `id` (Long): ID of the book to retrieve.
- **Response**:
  ```json
  {
    "id": 1,
    "title": "string",
    "author": "string",
    "isbn": "string",
    "available": true
  }
  ```

#### Add New Book
- **Endpoint**: `/api/books`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "title": "string",
    "author": "string",
    "isbn": "string"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Book added successfully"
  }
  ```

#### Update Book
- **Endpoint**: `/api/books/{id}`
- **Method**: `PUT`
- **Path Parameters**:
  - `id` (Long): ID of the book to update.
- **Request Body**:
  ```json
  {
    "title": "string",
    "author": "string",
    "isbn": "string",
    "available": true
  }
  ```
- **Response**:
  ```json
  {
    "message": "Book updated successfully"
  }
  ```

#### Delete Book
- **Endpoint**: `/api/books/{id}`
- **Method**: `DELETE`
- **Path Parameters**:
  - `id` (Long): ID of the book to delete.
- **Response**:
  ```json
  {
    "message": "Book deleted successfully"
  }
  ```

### 2. Patron Management

#### Get All Patrons
- **Endpoint**: `/api/patrons`
- **Method**: `GET`
- **Response**:
  ```json
  [
    {
      "id": 1,
      "name": "string",
      "contactInformation": "string"
    }
  ]
  ```

#### Get Patron by ID
- **Endpoint**: `/api/patrons/{id}`
- **Method**: `GET`
- **Path Parameters**:
  - `id` (Long): ID of the patron to retrieve.
- **Response**:
  ```json
  {
    "id": 1,
    "name": "string",
    "contactInformation": "string"
  }
  ```

#### Add New Patron
- **Endpoint**: `/api/patrons`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "name": "string",
    "contactInformation": "string"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Patron added successfully"
  }
  ```

#### Update Patron
- **Endpoint**: `/api/patrons/{id}`
- **Method**: `PUT`
- **Path Parameters**:
  - `id` (Long): ID of the patron to update.
- **Request Body**:
  ```json
  {
    "name": "string",
    "contactInformation": "string"
  }
  ```
- **Response**:
  ```json
  {
    "message": "Patron updated successfully"
  }
  ```

#### Delete Patron
- **Endpoint**: `/api/patrons/{id}`
- **Method**: `DELETE`
- **Path Parameters**:
  - `id` (Long): ID of the patron to delete.
- **Response**:
  ```json
  {
    "message": "Patron deleted successfully"
  }
  ```

### 3. Borrowing Records

#### Create Borrowing Record
- **Endpoint**: `/api/borrow/{bookId}/patron/{patronId}`
- **Method**: `POST`
- **Path Parameters**:
  - `bookId` (Long): ID of the book being borrowed.
  - `patronId` (Long): ID of the patron borrowing the book.
- **Response**:
  ```json
  {
    "message": "Booking created successfully"
  }
  ```

#### Return Book
- **Endpoint**: `/api/return/{bookId}/patron/{patronId}`
- **Method**: `POST`
- **Path Parameters**:
  - `bookId` (Long): ID of the book being returned.
  - `patronId` (Long): ID of the patron returning the book.
- **Response**:
  ```json
  {
    "message": "Book returned successfully"
  }
  ```

## Features

- **Caching**: Utilizes caching to enhance performance for frequently accessed data, including book details and patron information.
- **Logging**: Implements logging to track method calls, exceptions, and performance metrics. Logs are categorized into different levels such as `INFO`, `DEBUG`, and `ERROR`.
- **Validation**: Includes validation using annotations such as `@NotNull`, `@Size`, etc., to ensure the integrity of request parameters and body.

## Prerequisites

- Java 11 or later
- Maven
- PostgreSQL (to use other databases such as H2, you'll need to add the dependencies in `pom.xml` and adjust `application.properties`)

## Setup

1. **Clone the Repository:**

    ```bash
    git clone https://github.com/omar1896/Library-Management
    
    ```

2. **Configure Database (PostgreSQL):**

    Update the `application.properties` file with your PostgreSQL database configuration:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
    spring.datasource.username=yourusername
    spring.datasource.password=yourpassword
    ```

3. **Build and Run the Application:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. **Access the Application:**

    The application will be available at `http://localhost:8080`.

## Testing

To run unit tests, use the following Maven command:

```bash
mvn test
```


