# Spring Redis Project

A Spring Boot application demonstrating Redis integration with a clean architecture approach.

## Project Overview

This project implements a user management system using Redis as the data store, following clean architecture principles. It demonstrates the integration of Spring Boot with Redis, including test containers for testing.

## Architecture

The project follows clean architecture principles with the following layers:

```
com.redis/
├── app/                    # Application entry point
├── controller/            # REST controllers
├── domain/               # Domain layer
│   ├── adapter/         # Adapters for domain services
│   ├── ports/           # Ports (interfaces)
│   └── User.java        # Domain model
├── exception/           # Custom exceptions
└── infra/              # Infrastructure layer
    ├── adapters/       # Adapters for external services
    │   ├── entity/     # Redis entities
    │   └── repository/ # Repository implementations
    └── configuration/  # Configuration classes
```

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Redis
- TestContainers
- JUnit 5
- Maven

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Docker (for running tests with TestContainers)
- Redis server (for local development)

## Getting Started

### Building the Project

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring:boot run
```

The application will start on port 8080 by default.

### Running Tests

```bash
mvn test
```

## API Endpoints

### User Management

- `POST /api/users` - Create a new user
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID

## Testing

The project uses TestContainers for integration testing with Redis. Tests are configured to:
- Start a Redis container for each test class
- Clean up data between tests
- Use dynamic port allocation

### Test Structure

```java
@SpringBootTest(classes = SpringRedisApplication.class)
@Testcontainers
public class UserServiceTest {
    @Container
    static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:7.0-alpine"))
            .withExposedPorts(6379);
    
    // Test methods
}
```

## Configuration

### Redis Configuration

The application uses the following Redis properties (configurable in `application.properties`):

```properties
spring.data.redis.host=localhost
spring.data.redis.port=6379
```

### Test Configuration

For tests, Redis connection properties are dynamically configured using TestContainers:

```java
@DynamicPropertySource
static void redisProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.data.redis.host", redis::getHost);
    registry.add("spring.data.redis.port", () -> redis.getMappedPort(6379).toString());
}
```

## Dependencies

Key dependencies include:

- `spring-boot-starter-data-redis`: Redis integration
- `spring-boot-starter-web`: Web application support
- `testcontainers`: Container-based testing
- `junit-jupiter`: Testing framework
- `lombok`: Reducing boilerplate code




