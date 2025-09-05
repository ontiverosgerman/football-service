# Football Service

A lightweight Java microservice that provides RESTful APIs for managing football-related data—teams, players, matches, and statistics.

## 🧰 Tech Stack
- Java 17+
- Spring Boot
- Maven
- (Optional: H2 / PostgreSQL / MongoDB / MySQL)
- (Optional: Docker for containerization)

##  Features
- Create, fetch, update, and delete football entities (e.g., teams, players, matches)
- Basic validation and error handling
- JSON-based request/response
- (Optional) In-memory or persistent storage support
- (Optional) Unit and integration tests

##  Getting Started

### Prerequisites
- Java 17 or newer
- Maven
- Git

### Setup & Run
```bash
git clone https://github.com/ontiverosgerman/football-service.git
cd football-service
mvn clean install
mvn spring-boot:run
