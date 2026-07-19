# HealthIAM

> Enterprise Identity & Access Management (IAM) for Healthcare built with Spring Boot, Spring Security, OAuth2, OpenID Connect (OIDC), Keycloak, and PostgreSQL.

---

## Overview

HealthIAM is a production-style backend application that demonstrates enterprise authentication and authorization using modern Identity and Access Management (IAM) principles. The project delegates authentication to Keycloak while Spring Boot acts as an OAuth2 Resource Server responsible for validating JWT access tokens and enforcing authorization policies.

The application implements multiple authorization models including Role-Based Access Control (RBAC), Attribute-Based Access Control (ABAC), and Ownership-Based Authorization to secure patient data in a healthcare environment. This project serves as a practical reference for understanding enterprise security architecture commonly used in healthcare and other regulated industries.

---

# Architecture

```
                        +----------------------+
                        |      Keycloak        |
                        | Identity Provider    |
                        +----------+-----------+
                                   |
                        OAuth2 / OpenID Connect
                                   |
                                   ▼
                     Signed JWT Access Token
                                   |
                                   ▼
          +--------------------------------------------+
          |      Spring Boot Resource Server           |
          |                                            |
          |  • JWT Validation                          |
          |  • Role Mapping                            |
          |  • Method Security                         |
          |  • RBAC                                   |
          |  • ABAC                                   |
          |  • Ownership Authorization                |
          +----------------+---------------------------+
                           |
                           ▼
                   PostgreSQL Database
```

---

# Technology Stack

- Java 21
- Spring Boot
- Spring Security
- OAuth2 Resource Server
- OpenID Connect (OIDC)
- Keycloak
- PostgreSQL
- Spring Data JPA
- Maven
- JWT (JSON Web Token)

---

# Authentication Flow

1. User submits credentials to Keycloak.
2. Keycloak authenticates the user.
3. Keycloak issues a signed JWT Access Token.
4. Client sends the JWT in every API request.
5. Spring Security validates the JWT signature.
6. Realm roles are converted into Spring Security authorities.
7. Method-level authorization decides whether access should be granted.

---

# Authorization Models

## Role-Based Access Control (RBAC)

Implemented Roles

- SUPER_ADMIN
- DOCTOR
- PATIENT

Role-based authorization is enforced using Spring Security Method Security.

Example:

- SUPER_ADMIN → Full system access
- DOCTOR → Patient access
- PATIENT → Self-service access

---

## Attribute-Based Access Control (ABAC)

Authorization decisions are based on business attributes rather than roles alone.

Example:

Doctor JWT

Hospital = Apollo

↓

Patient Record

Hospital = Apollo

↓

Access Granted

Doctors cannot access patients belonging to another hospital.

---

## Ownership-Based Authorization

Patients are allowed to access only their own medical records.

Ownership is verified by comparing:

JWT

preferred_username

↓

Database

patient.username

Only if both values match is access granted.

---

# Security Features

- OAuth2 Authentication
- OpenID Connect (OIDC)
- JWT Authentication
- Keycloak Identity Provider
- Spring Security Resource Server
- Custom JwtAuthenticationConverter
- Realm Role Mapping
- Method-Level Security
- Role-Based Access Control (RBAC)
- Attribute-Based Access Control (ABAC)
- Ownership-Based Authorization
- Custom JWT Claims
- Protocol Mappers
- PostgreSQL Integration

---

# RBAC Authorization Matrix

| Role | Access |
|------|--------|
| SUPER_ADMIN | All Patients |
| DOCTOR | Patients from Same Hospital |
| PATIENT | Own Medical Record Only |

---

# Project Structure

```
src
├── controller
│     └── PatientController
│
├── entity
│     └── Patient
│
├── repository
│     └── PatientRepository
│
├── security
│     └── KeycloakJwtRoleConverter
│
├── service
│     └── PatientAuthorizationService
│
└── config
      └── SecurityConfig
```

---

# Keycloak Configuration

Realm

- Healthcare

Realm Roles

- SUPER_ADMIN
- DOCTOR
- PATIENT

Custom User Attributes

- hospital
- department

JWT Claims

- preferred_username
- hospital
- department
- realm_access.roles

---

# API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | /api/patients/{id} | Retrieve Patient Details |

---

# Sample Authorization Behaviour

| Logged In User | Patient 1 | Patient 2 |
|---------------|----------|----------|
| SUPER_ADMIN | ✅ | ✅ |
| Doctor (Apollo) | ✅ | ❌ |
| Doctor (Manipal) | ❌ | ✅ |
| Patient | ✅ Self | ❌ |
| Patient2 | ❌ | ✅ Self |

---

# Key Learning Outcomes

- Enterprise authentication using Keycloak
- OAuth2 Authorization Framework
- OpenID Connect (OIDC)
- JWT Authentication
- Spring Security Resource Server
- JWT Validation
- Custom JWT Authentication Converter
- Spring Method Security
- RBAC
- ABAC
- Ownership-Based Authorization
- PostgreSQL Integration
- Enterprise Identity and Access Management (IAM)

---

# Author

**Nandish Rao**

Enterprise Identity & Access Management using Spring Boot and Keycloak.