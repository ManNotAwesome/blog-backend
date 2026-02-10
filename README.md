# Blog Backend (Spring Boot)

A simple Blog REST API built using Spring Boot + MySQL + JWT Authentication.

## Features
- User Register + Login (JWT)
- Role-based access (USER / ADMIN)
- CRUD for Posts
- CRUD for Categories
- Pagination support
- Global Exception Handling

## Tech Stack
- Java 21
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven

# Blog Backend (Spring Boot)

A simple Blog REST API built using Spring Boot + MySQL + JWT Authentication.

## Features
- User Register + Login (JWT)
- Role-based access (USER / ADMIN)
- CRUD for Posts
- CRUD for Categories
- Pagination support
- Global Exception Handling

## Tech Stack
- Java 21
- Spring Boot
- Spring Security (JWT)
- Spring Data JPA
- MySQL
- Maven

## Setup

### 1) Clone the repo
```bash
git clone <your-repo-url>
cd blog-backend
2) Configure MySQL + JWT



cp src/main/resources/application-example.properties src/main/resources/application.properties


Then edit src/main/resources/application.properties and update:

spring.datasource.url

spring.datasource.username

spring.datasource.password

jwt.secret

3) Run the project
mvn spring-boot:run


Backend runs on:
http://localhost:8080

API Endpoints
Auth

POST /api/auth/register

POST /api/auth/login

Posts

POST /api/posts

GET /api/posts

GET /api/posts/{id}

PUT /api/posts/{id}

DELETE /api/posts/{id}

Categories

POST /api/categories

GET /api/categories

Admin

GET /api/admin/test (ADMIN only)

JWT Usage

Send token in request headers:
Authorization: Bearer <token>
