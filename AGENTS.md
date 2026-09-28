# AGENTS.md - Atlas Bank

## Architecture & Patterns
- **Hexagonal Architecture**: Strict separation between `domain`, `application`, and `infrastructure`.
- **Domain-Driven Design (DDD)**:
  - `domain`: Pure business logic, models, and domain services. No framework dependencies.
  - `application`: Use cases, ports (in/out), and application services.
  - `infrastructure`: Adapters for REST, Persistence (JPA), and external APIs.
- **Verification**: Architectural constraints are enforced via ArchUnit in `src/test/java/com/atlas/bank/atlas_bank/archtest`.

## Technical Stack
- **Java 21** | **Spring Boot 4.1.0**
- **Database**: H2 (In-memory)
- **Mapping**: MapStruct (with Lombok binding)
- **Security**: OAuth2 Resource Server

## Developer Commands
- **Build & Test**: `mvn clean install`
- **Run Application**: `mvn spring-boot:run`

## Conventions
- **Naming**: Follow the rules defined in `NamingConventionTest.java`.
- **Data Flow**: Request $\rightarrow$ REST Controller $\rightarrow$ Use Case (Port In) $\rightarrow$ Application Service $\rightarrow$ Domain Model $\rightarrow$ Repository Port (Port Out) $\rightarrow$ Persistence Adapter.
- **Mapping**: Always use MapStruct mappers to transition between layers (DTO $\leftrightarrow$ Domain $\leftrightarrow$ Entity).
