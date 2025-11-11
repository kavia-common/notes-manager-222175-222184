# Notes Manager Backend

Overview
- Spring Boot service offering CRUD APIs for Notes with validation, pagination, and search.
- Persistence: H2 in-memory by default; JPA auto-creates schema.
- OpenAPI/Swagger UI: /swagger-ui.html (redirect helper at /docs).

Process Flow
1. Create Note
   - POST /api/notes
   - Body: { "title": "string(1-200)", "content": "string(<=10000, optional)" }
   - Response: 201 { id, title, content, createdAt, updatedAt }
2. List Notes
   - GET /api/notes?page=0&size=10&q=term
   - Response: 200 { items: [...], page, size, total }
3. Get Note
   - GET /api/notes/{id}
   - Response: 200 { ... } or 404
4. Update Note
   - PUT /api/notes/{id}
   - Body: { "title": "string(1-200)", "content": "string(<=10000, optional)" }
   - Response: 200 { ... } or 404/400
5. Delete Note
   - DELETE /api/notes/{id}
   - Response: 204 or 404

Compliance
- Validation: Bean Validation with structured error responses (400).
- Errors: 404 Not Found when note is missing; 500 generic.
- CORS: Enabled for http://localhost:3000 and http://localhost:5173.

Review Notes
- H2 console available at /h2-console (login: jdbc:h2:mem:testdb).
- Pagination limits size to max 200.
- Search is case-insensitive title contains.

Run
- From notes_backend: ./gradlew bootRun
- Tests: ./gradlew test

Reviewed & Approved by Engineering.