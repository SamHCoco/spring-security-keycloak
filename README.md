# Spring Security - Keycloak

Spring Security integration with Keycloak 26 for authentication and authorization.

## Prerequisites

- Java 17
- Maven 3.6+
- Keycloak 17+ (Tested for Keycloak 26.4.5)

## Bill of Materials

### Core Dependencies
- **Spring Boot**: 3.4.11
- **Spring Security**: Inherited
- **Spring Security OAuth2 Resource Server**: Inherited
- **Spring OAuth2 Client**: Inherited
- **Spring Web**: Inherited
- **Lombok**: Inherited

### Test Dependencies
- **Spring Boot Test**: Inherited
- **Spring Security Test**: Inherited

### Build
- **Spring Boot Maven Plugin**: Inherited

## Quick Start

```bash
mvn clean install
mvn spring-boot:run