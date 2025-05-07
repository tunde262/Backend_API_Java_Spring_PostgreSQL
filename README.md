# Revature P1 Backend

This is a Java Spring Boot backend application that supports a product-based system with JWT-protected authentication (i.e. Inventory Management System Backend API). It is connected to a PostgreSQL database (via Docker), supports user registration and login using bcrypt password hashing, and protects all product-related routes with JSON Web Tokens.

![thumbnail](https://github.com/tunde262/Revature_P1_Backend/blob/main/assets/thumbnail.png?raw=true)


---

## Tech Stack

- **Java**
- **Spring Boot**
- **Spring Web**
- **PostgreSQL** (via Docker)
- **JDBC Template**
- **JWT)**
- **BCrypt**

---

## Authentication & Security

- **JWT-based Authentication**
  - Login and registration return a JWT token.
  - All `/api/products/**` routes are protected via middleware that decodes the JWT.
- **Password Hashing**
  - Passwords are hashed with `BCrypt` before storing in the database.

---

## Features

### User Endpoints (`/api/users`)
| Method | Endpoint       | Description              | Auth |
|--------|----------------|--------------------------|------|
| POST   | `/register`    | Register new user        | ❌   |
| POST   | `/login`       | Login and get JWT token  | ❌   |
| GET    | `/me`          | Get current user profile | ✅   |

### Product Endpoints (`/api/products`)
| Method | Endpoint               | Description                     | Auth |
|--------|------------------------|----------------------------------|------|
| GET    | `/`                    | Fetch all products or search    | ✅   |
| GET    | `/:productId`          | Fetch product by ID             | ✅   |
| POST   | `/`                    | Add new product                 | ✅   |
| PUT    | `/:productId`          | Update existing product         | ✅   |
| DELETE | `/:productId`          | Delete a product                | ✅   |

---

## Running the App

### Prerequisites

- Java 17
- Maven
- Docker (for PostgreSQL)

### Steps

1. Start PostgreSQL:

```bash
docker container run --name revature-p1-database -e POSTGRES_PASSWORD=password -d -p 5431:5432 postgres
```

2. Run Spring Boot app

---

## Testing (Backend Only)

Use tools like Postman or Hoppscotch to test:

1. Register: `POST /api/users/register`

2. Login: `POST /api/users/login`

3. Use the returned JWT as `Authorization: Bearer <token>` header for all product requests.
