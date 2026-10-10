# FutureHire Backend

Spring Boot 4.1 REST API using Java 21, Spring Security/JWT, Spring Data JPA, MySQL in production, and H2 locally.

## Local Run

The default `local` profile uses an in-memory H2 database and seeds sample content plus a student account. It does not create an administrator unless bootstrap variables are set.

```powershell
$env:BOOTSTRAP_ADMIN_EMAIL = "admin@localhost"
$env:BOOTSTRAP_ADMIN_PASSWORD = "change-this-local-password"
mvn spring-boot:run
```

API: `http://localhost:8081`; Swagger UI: `http://localhost:8081/swagger-ui.html`.

## Production

Set `SPRING_PROFILES_ACTIVE=prod` and provide:

- `SPRING_DATASOURCE_URL` as a JDBC MySQL URL.
- `SPRING_DATASOURCE_USERNAME` and `SPRING_DATASOURCE_PASSWORD`.
- `JWT_SECRET`, a unique random value at least 32 bytes long.
- `CORS_ORIGINS`, an exact comma-separated list of frontend origins.
- Optional first-run admin bootstrap variables: `BOOTSTRAP_ADMIN_EMAIL`, `BOOTSTRAP_ADMIN_PASSWORD`, `BOOTSTRAP_ADMIN_NAME`.
- `PORT` if required by the hosting platform.

Set the bootstrap email and password together on the first start, then remove both variables after verifying that the administrator account was created. The Dockerfile selects `prod`. Never use the `local` profile or its development signing key in production. Demo student seeding is disabled in `prod`; seeded content remains enabled. Review database backups/schema changes before upgrades because Hibernate currently uses `ddl-auto=update`.

## API Notes

- `/api/auth/**` handles student registration and login.
- `/api/student/**` requires a student or admin JWT.
- `/api/admin/**` requires an admin JWT.
- Coding submissions are persisted as `SUBMITTED`; this project does not include a code execution sandbox.
- Resume file bytes are stored in MySQL; no ATS analysis engine is configured.
- Study-resource records point to administrator-hosted URLs; the backend does not upload those files.
