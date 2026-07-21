# Campus Management System

## Project Overview

A role-based Campus Management System built with Spring Boot.

Current Architecture:
- Monolithic
- REST API
- JWT Authentication
- Spring Security
- PostgreSQL
- JPA/Hibernate
- MapStruct
- DTO Architecture
- RBAC (Role Based Access Control)

---

# Tech Stack

Backend
- Java 26
- Spring Boot 4.x
- Spring Security
- Spring Data JPA
- Hibernate

Database
- PostgreSQL

Authentication
- JWT (Bearer Token)

Build Tool
- Maven

Utilities
- Lombok
- MapStruct

---

# Project Structure

```
src/main/java/com/campusmanagement

common
├── exception
├── security
└── util

auth
├── controller
├── dto
├── mapper
├── service
└── ...

user
role
verification
student
faculty
department
course
semester
attendance
exam
notice
assignment
```

Each feature follows the same package structure.

```
feature
│
├── controller
├── service
│    ├── FeatureService
│    └── impl
├── repository
├── entity
├── dto
├── mapper
└── enums
```

---

# Layer Responsibilities

Controller

- Receives HTTP Requests
- Validates Request DTOs
- Returns ResponseEntity
- No business logic

Service

- Contains business logic
- Calls repositories
- Uses mappers
- Throws business exceptions

Repository

- Database access only

Mapper

- Converts Entity ↔ DTO
- Uses MapStruct

DTO

- API contract only
- Contains validation annotations

Entity

- Database representation
- Contains JPA annotations only

---

# Authentication Flow

Register

Client
↓

POST /api/auth/register

↓

Password BCrypt Encoded

↓

User Saved

↓

ROLE_UNVERIFIED Assigned

---

Login

Client

↓

POST /api/auth/login

↓

AuthenticationManager

↓

JWT Generated

↓

JWT Returned

---

Authenticated Request

Client

↓

Authorization: Bearer <JWT>

↓

JwtFilter

↓

Validate Token

↓

Load User

↓

Security Context

↓

Controller

---

# Authorization

Method Security is preferred.

Example

```java
@PreAuthorize("hasRole('ADMIN')")
```

Avoid placing role logic inside controllers.

Business logic belongs in services.

---

# Roles

Current Roles

- ADMIN
- STUDENT
- FACULTY
- ALUMNI
- UNVERIFIED

Every new user starts as

ROLE_UNVERIFIED

Verification changes roles.

---

# Verification Module

Workflow

Register

↓

ROLE_UNVERIFIED

↓

Submit Verification

↓

Status = PENDING

↓

Admin Reviews

↓

Approved

↓

Remove ROLE_UNVERIFIED

↓

Assign Requested Role

---

Verification Status

- PENDING
- APPROVED
- REJECTED

Requested Roles

- STUDENT
- FACULTY
- ALUMNI

---

# API Conventions

Base URL

```
/api
```

No versioning.

Correct

```
/api/auth/login
```

Avoid

```
/api/v1/auth/login
```

---

# Controller Conventions

Return

```java
ResponseEntity<ResponseDto>
```

Never return entities directly.

---

# DTO Naming

Request DTO

```
LoginRequest
```

Response DTO

```
LoginResponse
```

Decision DTO

```
VerificationDecisionRequest
```

Never expose entities.

---

# Repository Conventions

Repository interfaces extend

```java
JpaRepository<Entity, Long>
```

Method names use Spring Data conventions.

Example

```java
findByEmail()

findFirstByUserOrderBySubmittedAtDesc()
```

Prefer derived queries before custom JPQL.

---

# Entity Conventions

Relationships

Prefer

```java
FetchType.LAZY
```

Avoid

```java
FetchType.EAGER
```

unless required.

Use enums with

```java
EnumType.STRING
```

Never ORDINAL.

---

# Validation

Validation belongs in DTOs.

Example

```java
@NotBlank
@NotNull
@Email
@Size
```

Entities should not contain request validation.

---

# Security Principles

Passwords

- BCrypt

JWT

- Stateless Authentication

Roles

- Stored in Database

Authorization

- Method Level Security

---

# Coding Standards

Constructor Injection

✔

```java
@RequiredArgsConstructor
```

Avoid field injection.

Prefer

```java
private final
```

Use Lombok

```java
@Getter
@Setter
@Builder
```

Keep methods short.

Keep controllers thin.

---

# Naming Conventions

Entity

Student

Repository

StudentRepository

Service

StudentService

Implementation

StudentServiceImpl

Mapper

StudentMapper

Controller

StudentController

---

# Future Modules

Core

- Student
- Faculty
- Department
- Course
- Semester

Academic

- Enrollment
- Attendance
- Exam
- Marks

Utilities

- Notice
- Assignment
- Dashboard
- Reports

---

# Planned Improvements

- Global Exception Handling
- File Upload Service
- Email Verification
- Password Reset
- Refresh Tokens
- Audit Logging
- Docker
- CI/CD
- Redis Caching
- Microservice Migration

---

# Design Principles

- Separation of Concerns
- Single Responsibility Principle
- Dependency Injection
- Stateless Authentication
- DTO Driven APIs
- Feature-Based Packaging
- Reusable Components
- Clean Code