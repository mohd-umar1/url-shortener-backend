# Shorty — URL Shortener Backend

A URL shortening service built with **Java, Spring Boot, and MySQL**. Shorty provides the backend infrastructure for creating shortened links, redirecting users to their original URLs, managing links, and retrieving click analytics through REST API endpoints.

Designed to integrate with a React frontend, the application separates request handling, business logic, and database operations into maintainable backend components.

## Overview

Long URLs are inconvenient to share and difficult to manage. Shorty transforms long URLs into compact, shareable links and provides functionality for managing links and tracking their usage.

### Core Workflow

1. A client submits a long URL through the REST API.
2. The backend generates a short identifier and stores the URL mapping.
3. The client receives a shortened URL.
4. When the short link is accessed, the backend resolves the identifier and redirects the user to the original destination.
5. Analytics endpoints provide information about link activity.

## Tech Stack

| Technology      | Purpose                                        |
| --------------- | ---------------------------------------------- |
| Java            | Backend programming language                   |
| Spring Boot     | Application framework                          |
| Spring Web      | REST API development and HTTP request handling |
| Spring Data JPA | Database access and persistence                |
| Hibernate       | Object-relational mapping                      |
| MySQL           | Relational database                            |
| Gradle          | Dependency management and build automation     |

## Architecture

The application follows a layered backend architecture to separate responsibilities and simplify maintenance.

```text
          React Frontend
                |
                v
          REST Controllers
                |
                v
           Service Layer
          (Business Logic)
                |
                v
          Repository Layer
          (Spring Data JPA)
                |
                v
              MySQL
```

* **Controller Layer:** Handles incoming HTTP requests and returns API responses.
* **Service Layer:** Implements URL-shortening workflows and application logic.
* **Repository Layer:** Performs database operations through Spring Data JPA.
* **Database Layer:** Persists URL mappings and related application data.

## Key Features

* **URL Shortening:** Generate compact links from long URLs.
* **URL Redirection:** Resolve short identifiers and redirect users to original destinations.
* **URL Management:** Retrieve and manage shortened URLs through API requests.
* **Click Analytics:** Access link usage statistics through analytics endpoints.
* **REST API Integration:** Enable communication with the React frontend.
* **Database Persistence:** Store application data in MySQL using JPA and Hibernate.

## Getting Started

### Prerequisites

Make sure you have installed:

* Java JDK compatible with the project
* MySQL Server
* Gradle, or the Gradle Wrapper included in the repository

### 1. Clone the Repository

```bash
git clone <YOUR_BACKEND_REPOSITORY_URL>
cd <YOUR_BACKEND_DIRECTORY>
```

### 2. Create the Database

Create the MySQL database used by the application:

```sql
CREATE DATABASE urlshortnerdb;
```

Replace the database name if your application uses a different one.

### 3. Configure Database Credentials

Configure your database connection in `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/urlshortnerdb}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set the `DB_USERNAME` and `DB_PASSWORD` environment variables before starting the application. Adjust these settings to match your existing configuration.

**Security:** Never commit database passwords, API keys, or other sensitive credentials to GitHub.

### 4. Run the Application

Using the Gradle Wrapper on Windows:

```powershell
.\gradlew.bat bootRun
```

On Linux or macOS:

```bash
./gradlew bootRun
```

Alternatively, if Gradle is installed globally:

```bash
gradle bootRun
```

The application will start on the configured port. If no custom port is specified, Spring Boot defaults to `8080`.

## API Overview

The backend exposes endpoints for interacting with shortened URLs and their associated data.

| Functionality   | Description                                       |
| --------------- | ------------------------------------------------- |
| URL Creation    | Submit a long URL and receive a shortened link    |
| URL Redirection | Resolve a short link to its original destination  |
| URL Listing     | Retrieve shortened URLs associated with a user    |
| Link Analytics  | Retrieve click statistics for a link              |
| User Operations | Support account-related workflows, if implemented |

Refer to the application's controller mappings for the actual endpoint paths, HTTP methods, request formats, response schemas, and authentication requirements.

## Frontend Integration

Shorty is designed to integrate with a React frontend that provides the interface for URL shortening, link management, and analytics.

To connect the applications:

1. Start the Spring Boot backend.
2. Configure the frontend API base URL.
3. Configure CORS to allow requests from the frontend origin.
4. Ensure API requests use the correct endpoint paths and authentication headers.

## Future Improvements

* Custom short aliases
* Link expiration and lifecycle management
* Advanced click analytics and reporting
* Rate limiting and abuse prevention
* API documentation using Swagger / OpenAPI
* Docker-based deployment and automation

## Author

**Umar Inamdar**

GitHub: [@mohd-umar1](https://github.com/mohd-umar1)

---

*Shorty — Shorten links. Simplify sharing.*
