# Notes Backend (Spring Boot)

Overview
- CRUD for Notes with validation, pagination, and title search.
- H2 in-memory DB, Swagger UI at /swagger-ui.html (helper: /docs).
- CORS enabled for http://localhost:3000 and http://localhost:5173.

Endpoints
- POST /api/notes -> 201 {id,title,content,createdAt,updatedAt}
- GET /api/notes?page&size&q -> 200 {items,page,size,total}
- GET /api/notes/{id} -> 200 {...} or 404
- PUT /api/notes/{id} -> 200 {...} or 404/400
- DELETE /api/notes/{id} -> 204 or 404

Validation
- title: required, 1-200 chars
- content: optional, <= 10000 chars
- 400 response includes field errors map.

Run
- ./gradlew bootRun
- Tests: ./gradlew test

H2 Console
- /h2-console
- JDBC URL: jdbc:h2:mem:testdb

```text
Reviewed & Approved by Engineering.
```
