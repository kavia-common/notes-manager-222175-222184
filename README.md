# Notes Manager - Backend (Spring Boot)

A simple notes manager backend providing CRUD operations with an in-memory H2 database by default.

## Quick Start

- Java 17+
- Gradle Wrapper is included

Run:
```
cd notes_backend
./gradlew bootRun
```

The app starts on port 3001.

- Swagger UI: http://localhost:3001/swagger-ui.html
- API Docs (OpenAPI JSON): http://localhost:3001/api-docs
- H2 Console: http://localhost:3001/h2-console (JDBC URL: jdbc:h2:mem:notesdb, user: sa, password: empty)

## Endpoints

Base path: /api/notes

- GET /api/notes?page=0&size=10&sort=createdAt,desc
- GET /api/notes/{id}
- POST /api/notes
- PUT /api/notes/{id}
- DELETE /api/notes/{id}

Validation:
- title: non-blank, max 200
- content: optional, max 5000

Errors:
- 400 returns { message, error, status, path, timestamp }
- 404 returns { message, error, status, path, timestamp }

## Sample cURL

List:
```
curl -s "http://localhost:3001/api/notes?page=0&size=5&sort=createdAt,desc"
```

Create:
```
curl -s -X POST "http://localhost:3001/api/notes" \
  -H "Content-Type: application/json" \
  -d '{"title":"My Note","content":"Hello world"}'
```

Get by ID:
```
curl -s "http://localhost:3001/api/notes/<UUID>"
```

Update:
```
curl -s -X PUT "http://localhost:3001/api/notes/<UUID>" \
  -H "Content-Type: application/json" \
  -d '{"title":"Updated","content":"Updated content"}'
```

Delete:
```
curl -s -X DELETE "http://localhost:3001/api/notes/<UUID>" -i
```

## CORS
CORS is enabled for typical localhost dev ports (3000, 5173).

## Profiles / DB
No external DB required. H2 in-memory is used by default with schema auto-generation.

Reviewed & Approved by Engineering.