Shorty — URL Shortener Backend

A URL shortening service built with Java, Spring Boot, and MySQL. Shorty provides the backend infrastructure for creating shortened links, redirecting users to their original URLs, managing links, and retrieving click analytics through API endpoints.

Designed to integrate with a React frontend, the application separates request handling, business logic, and database operations into maintainable backend components.

Overview

Long URLs are inconvenient to share and difficult to manage. Shorty provides a backend service that transforms long URLs into compact links and supports tracking their usage.

Core workflow

A client submits a long URL through the API.
The backend generates a short identifier and stores the URL mapping.
The client receives a shortened URL.
When the short link is accessed, the backend resolves the identifier and redirects to the original destination.
Analytics endpoints provide information about link activity.
Tech Stack
Technology	Purpose
Java	Backend programming language
Spring Boot	Application framework and REST API development
Spring Web	HTTP request handling and REST controllers
Spring Data JPA	Database access and persistence
Hibernate	Object-relational mapping
MySQL	Relational database
Maven	Dependency management and build automation
Architecture

The application follows a layered backend architecture to separate responsibilities and simplify maintenance.

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
Controller layer: Handles incoming HTTP requests and returns API responses.
Service layer: Implements URL-shortening workflows and application logic.
Repository layer: Performs database operations through Spring Data JPA.
Database layer: Persists URL mappings and related application data.
Key Capabilities
URL Shortening: Generate compact links from long URLs.
URL Redirection: Resolve short identifiers and redirect to original destinations.
Link Management: Retrieve and manage shortened URLs through API requests.
Click Analytics: Access link usage statistics through analytics endpoints.
Frontend Integration: Provide REST APIs for a React-based client.
Persistent Storage: Store application data in MySQL using JPA and Hibernate.

The capabilities listed above should match the features implemented in your current codebase.

Getting Started
Prerequisites
Java JDK compatible with the project
MySQL Server
Maven, or the Maven wrapper included in the repository
1. Clone the repository
git clone <YOUR_BACKEND_REPOSITORY_URL>
cd <YOUR_BACKEND_DIRECTORY>
2. Create the database

Open MySQL and execute:

CREATE DATABASE urlshortnerdb;

Use your actual database name if it differs.

3. Configure environment variables

Configure the database connection in src/main/resources/application.properties:

spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/urlshortnerdb}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

Set DB_USERNAME and DB_PASSWORD in your environment before launching the application. Adapt these properties to your existing configuration.

Never commit database credentials or other secrets to version control.

4. Run the application

On Windows, using the Maven wrapper:

.\mvnw.cmd spring-boot:run

Alternatively, with Maven installed:

mvn spring-boot:run

The API will be available on the configured application port. If no custom port is specified, Spring Boot defaults to 8080.

API Overview

The backend exposes endpoints for interacting with shortened URLs and their associated data.

Functionality	Description
URL creation	Submit a long URL and receive a shortened link
URL redirection	Resolve a short link to its destination
URL listing	Retrieve shortened URLs associated with a user
Link analytics	Retrieve click statistics for a link
User operations	Support account-related workflows, if implemented

For exact endpoint paths, request formats, response schemas, and authentication requirements, refer to the application's controller mappings.

Frontend

Shorty is designed to work with a React frontend that provides the user interface for URL shortening, link management, and analytics.
Author

Umar Inamdar

GitHub: mohd-umar1

Shorty — A backend service for shorter links and simpler sharing.

The frontend communicates with the backend through HTTP requests. During local development, configure the correct API base URL and allow the frontend origin through Spring Boot CORS configuration.
